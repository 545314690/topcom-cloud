package com.topcom.cms.yuqing.controller;

import com.topcom.cms.common.page.DateParam;
import com.topcom.cms.common.utils.DateUtil;
import com.topcom.cms.data.domain.News;
import com.topcom.cms.es.service.CommentsService;
import com.topcom.cms.es.service.NewsService;
import com.topcom.cms.es.vo.AggRequest;
import com.topcom.cms.es.vo.KV;
import com.topcom.cms.es.vo.Keyword;
import com.topcom.cms.mongo.domain.Accident;
import com.topcom.cms.mongo.service.AccidentService;
import com.topcom.cms.yuqing.domain.Keywords;
import com.topcom.cms.yuqing.utils.BaiduApiUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RestController
@RequestMapping("/accidentYuqing/")
@Api("事故舆情接口")
public class AccidentYuqingController {

    private String SENSITIVE_WORDS = "有限责任公司|有限公司|公司|集团|省|市|县|镇|乡|村|自治区|自治县|自治区|自治州|旗|煤矿|煤业";
    private String[] MUNICIPALITY = new String[]{"新疆","西藏","宁夏","内蒙古"};

    private String[] SGJB=new String[]{"一般事故","较大事故","重大事故","特大事故"};
    @Autowired
    NewsService newsService;
    @Autowired
    CommentsService commentsService;
    @Autowired
    AccidentService accidentService;

    /**
     * 获取死亡人数前5名的事故文章数量
     *
     * @param request
     * @param dateParam
     * @return
     */
    @RequestMapping(value = "/hotAccident", method = RequestMethod.POST)
    public List<KV> hotAccident(HttpServletRequest request, @RequestBody DateParam dateParam) {
        List<Accident> accidentList = accidentService.getTopNAccidents(dateParam, 5);
        List<KV> kvs = new ArrayList<>();
        for (Accident accident : accidentList) {
            //事故概要
            String outline = accident.outline();
            long count = getAccidentArticle(dateParam, accident).getTotalElements();
            kvs.add(new KV(outline, count));
        }
        kvs.sort(new Comparator<KV>() {
            @Override
            public int compare(KV o1, KV o2) {
                return o1.compareTo(o2);
            }
        });
        return kvs;
    }

    /**
     * 获取死亡人数前5名的事故文章的评论数量
     *
     * @param request
     * @param dateParam
     * @return
     */
    @RequestMapping(value = "/hotAccidentComment", method = RequestMethod.POST)
    public List<KV> hotAccidentComment(HttpServletRequest request, @RequestBody DateParam dateParam) {
        List<Accident> accidentList = accidentService.getTopNAccidents(dateParam, 5);
        List<KV> kvs = new ArrayList<>();
        for (Accident accident : accidentList) {
            //事故概要
            String outline = accident.outline();
            AggRequest aggRequest = new AggRequest();
            aggRequest.setDate(dateParam);
            String companyName = accident.getCompanyName();
            String atype = accident.getAtype();
            String city = accident.getCity();
            com.topcom.cms.es.vo.Keyword keywords = null;
            if (StringUtils.isNotBlank(companyName)) {
                keywords = new Keyword(companyName, atype + "@" + city);
            } else {
                keywords = new Keyword("事故", atype + "@" + city);
            }
            aggRequest.setKeyword(keywords);
            long count = commentsService.countByArticle(aggRequest);
            kvs.add(new KV(outline, count));
        }
        kvs.sort(new Comparator<KV>() {
            @Override
            public int compare(KV o1, KV o2) {
                return o1.compareTo(o2);
            }
        });
        return kvs;
    }

    private Page<News> getAccidentArticle(DateParam dateParam, Accident accident) {
        String companyName = accident.getCompanyName();
        String atype = accident.getAtype();
        String city = accident.getCity();
        Keywords keywords = null;
        if (StringUtils.isNotBlank(companyName)) {
            keywords = new Keywords(companyName, atype + "@" + city);
        } else {
            keywords = new Keywords("事故", atype + "@" + city);
        }
        Page<News> page = newsService.findByMustShouldDateInType(keywords.getMustWord(), keywords.getShouldWord(), keywords.getMustNotWord()
                , DateUtil.dateToString(dateParam.startDate()), DateUtil.dateToString(dateParam.endDate())
                , "content", 0, 1, "pubTime", 1);
        return page;
    }

    @RequestMapping(value = "/saveAccident", method = RequestMethod.POST)
    public Accident saveAccident(HttpServletRequest request, @RequestBody Accident entity) {
        return accidentService.save(handleOneAccident(entity));
    }

    @RequestMapping(value = "/saveAccidents", method = RequestMethod.POST)
    public Iterable<Accident> saveAccidents(HttpServletRequest request, @RequestBody Iterable<Accident> entities) {
        for (Accident acc:entities){
            handleOneAccident(acc);
        }
        return accidentService.save(entities);
    }

    @ApiOperation("根据id更新实体")
    @RequestMapping(
            value = {"/{id}"},
            method = {RequestMethod.PUT},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public Accident updateAccident(@PathVariable String id, @RequestBody Accident entity) {
        entity.setId(String.valueOf(id));
        entity.setDateModified(new Date());
        accidentService.save(handleOneAccident(entity));
        return entity;
    }


    /**
     * 获取事故关键词用于es查询
     * 把省市县公司集团煤矿等去掉，列为同现词，再加入时间，月与日
     * 如 江西显亮煤业有限公司崇义县煤矿 2017-08-01 20:20:00   ---->>江西显亮@@崇义@@@7月1日
     * @param id
     * @return
     */
    @RequestMapping(
            value = {"/keyWords/{id}"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public JSONObject getKeyWords(@PathVariable String id) {
        Accident accident = this.accidentService.findById(id);
        Date date = accident.getAdate();
        String companyFullName = accident.getCompanyFullName();
        String mustWord = companyFullName.replaceAll(SENSITIVE_WORDS,"@");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month =calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH)+1;
        mustWord=mustWord+"@"+month+"月"+day+"日";
        JSONObject json = new JSONObject();
        json.put("mustWord",mustWord);
        return json;
    }


    private Accident handleOneAccident(Accident entity){
        int deathnumber = entity.getDeathnumber();
        String sgjb = entity.getSgjb();
        String cityinfo = entity.getCityinfo();
        String originaltime = entity.getOriginaltime();
        if (!StringUtils.isBlank(originaltime)){
            entity.setAdate(DateUtil.parseDate(originaltime));
            entity.setAtime(DateUtil.parseDate(originaltime).getTime()/1000);
        }
        if (StringUtils.isEmpty(sgjb)){
            if (deathnumber<3){
                sgjb = SGJB[0];
            }else if (deathnumber<10){
                sgjb = SGJB[1];
            }else if (deathnumber<30){
                sgjb = SGJB[2];
            }else {
                sgjb = SGJB[3];
            }
            entity.setSgjb(sgjb);
        }
        if (StringUtils.isEmpty(cityinfo)){
            entity.setCityinfo(getProvinInfo(entity));
        }
        String address = entity.getProvince() + entity.getCity() + entity.getCounty();
        BaiduApiUtil.Geo geo = BaiduApiUtil.getGeo(address);
        if (geo != null) {
            entity.setLat(geo.getLat());
            entity.setLng(geo.getLng());
        }
        return entity;
    }
    private String getProvinInfo(Accident acc){
        String county = acc.getCounty();
        String city = acc.getCity();
        String province = acc.getProvince();

        if (!StringUtils.isBlank(acc.getCityinfo())){
            return acc.getCityinfo();
        }
        Set<String> municipality = new HashSet<>();
        municipality.addAll(Arrays.asList(MUNICIPALITY));
        if (municipality.contains(province)){
            return province+"自治区"+(city==null?"":(city+"市"))+(county==null?"":(county+"县"));
        }
        return (province==null?"":(province+"省"))+(city==null?"":(city+"市"))+(county==null?"":(county+"县"));
    }
}

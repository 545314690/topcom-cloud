package com.topcom.cms.web.spring.controller;

import com.topcom.cms.base.web.spring.controller.GenericController;
import com.topcom.cms.domain.Group;
import com.topcom.cms.domain.SearchWord;
import com.topcom.cms.domain.User;
import com.topcom.cms.service.SearchWordManager;
import com.topcom.cms.service.UserManager;
import com.topcom.cms.web.bind.annotation.CurrentUser;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 */
@Controller
@RequestMapping("/searchWord")
public class SearchWordController extends GenericController<SearchWord, Long, SearchWordManager> {


    @Autowired
    UserManager userManager;
    private SearchWordManager searchWordManager;

    @Autowired
    public void setSearchWordManager(SearchWordManager searchWordManager) {
        this.searchWordManager = searchWordManager;
        this.manager = this.searchWordManager;
    }


    /**
     * 增加点击次数 对于单个搜索词 可以支持累加
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/add", produces = "application/json")
    @ResponseBody
    public SearchWord addClickCount(@CurrentUser User user,@ApiParam("Word") @RequestParam(required = true) String word,
                                    @ApiParam("type") @RequestParam(required = true) Integer type){
        User user1 = this.userManager.findById(user.getId());
        Set<Group> groups = user1.getGroups();
        return searchWordManager.addClickCount(groups,word,type);
    }


    /**
     * 热词查询 查询排名前 limit 的word
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/findByType", produces = "application/json")
    @ResponseBody
    public List<SearchWord> findByType(@CurrentUser User user,@ApiParam("limit") @RequestParam(required = true) Integer limit,
                                       @ApiParam("type") @RequestParam(required = true) Integer type){
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,"wordCount"));
        Pageable page = new PageRequest(0,limit,sort);
        User user1 = this.userManager.findById(user.getId());
        Set<Group> groups = user1.getGroups();
        String groupId = SearchWord.groupIdBySet(groups);
        List<String> groupIdList = new ArrayList<>();
        groupIdList.add(groupId);
        if (groupId.indexOf(",")!=-1){
            String[] split = groupId.split(",");
            for (String s:split){
                groupIdList.add(s);
            }
        }
        Page<SearchWord> searchWords =  this.searchWordManager.findByTypeAndGroupIdIn(page,type,groupIdList);

        return  filterNull(searchWords);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findByWord", produces = "application/json")
    @ResponseBody
    public List<SearchWord> findByWord(@CurrentUser User user,@ApiParam("limit") @RequestParam(required = true) Integer limit,
                                       @ApiParam("word") @RequestParam(required = true) String word){
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,"wordCount"));
        Pageable page = new PageRequest(0,limit,sort);
        User user1 = this.userManager.findById(user.getId());
        Set<Group> groups = user1.getGroups();
        String groupId = SearchWord.groupIdBySet(groups);
        List<String> groupIdList = new ArrayList<>();
        groupIdList.add(groupId);
        if (groupId.indexOf(",")!=-1){
            String[] split = groupId.split(",");
            for (String s:split){
                groupIdList.add(s);
            }
        }
        Page<SearchWord> searchWords =  this.searchWordManager.findByWordAndGroupIdIn(page,word,groupIdList);

        return filterNull(searchWords);
    }


    private List<SearchWord> filterNull(Page<SearchWord> searchWords){
        List<SearchWord> result = new ArrayList<>();
        Set<String> s_set = new HashSet<>();
        for (int i=0;i<searchWords.getContent().size();i++){
            SearchWord searchWord = searchWords.getContent().get(i);
            if (!StringUtils.isEmpty(searchWord.getWord())){
                if (!result.contains(searchWord)){
                    result.add(searchWord);
                }
            }
        }
        return result;
    }

}

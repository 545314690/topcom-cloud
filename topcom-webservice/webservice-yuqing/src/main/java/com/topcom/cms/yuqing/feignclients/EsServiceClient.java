package com.topcom.cms.yuqing.feignclients;

import com.topcom.cms.data.domain.ESBaseDomain;
import com.topcom.cms.yuqing.vo.request.AggRequest;
import com.topcom.cms.yuqing.vo.request.BoolQueryRequest;
import com.topcom.cms.yuqing.vo.request.KV;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by topcom on 2017/9/25 0025.
 */

@FeignClient(value = "es-service")
//@FeignClient(value = "es-service", fallback = EsServiceClientHystrix.class)
public interface EsServiceClient {

    //commentsService.countByArticle(aggRequest)


    @RequestMapping(method = RequestMethod.POST, value = "/comment/countByArticle")
    Long countByArticle(@RequestBody AggRequest aggRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/es/findPageByMustShouldparamSearch")
    Page findPageByMustShouldparamSearch(@RequestBody BoolQueryRequest boolQueryRequest);

    @RequestMapping(method = RequestMethod.GET, value = "/es/findById/{id}")
    ESBaseDomain findArticleById(@PathVariable(value = "id") String id);


    @RequestMapping(method = RequestMethod.POST, value = "/es/filterAndGroupBy")
    List<KV> filterAndGroupBy(@RequestBody BoolQueryRequest boolQueryRequest);
}

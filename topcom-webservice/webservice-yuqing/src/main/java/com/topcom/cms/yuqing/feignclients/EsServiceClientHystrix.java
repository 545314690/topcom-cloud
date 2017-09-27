package com.topcom.cms.yuqing.feignclients;

import com.topcom.cms.yuqing.vo.request.AggRequest;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by topcom on 2017/9/25 0025.
 */
public class EsServiceClientHystrix {
    public Long countByArticle(@RequestBody AggRequest aggRequest) {
        return 0l;
    }
}

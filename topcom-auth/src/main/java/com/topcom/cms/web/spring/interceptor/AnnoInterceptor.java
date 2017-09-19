package com.topcom.cms.web.spring.interceptor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by lism on 17-6-13.
 */
//@ConfigurationProperties(prefix = "anno",locations = "classpath:auth.properties")
    //1.4以后取消了location 两种解决方案 1如下替代
   // 2监听ApplicationEnvironmentPreparedEvent事件，通过ResourceLoader加载自定义的配置文件
@Configuration
@Component
@PropertySource("classpath:auth.properties")
@ConfigurationProperties(prefix="anno")
public class AnnoInterceptor {

    private String[] excludePaths;

    public String[] getExcludePaths() {
        return excludePaths;
    }

    public void setExcludePaths(String[] excludePaths) {
        this.excludePaths = excludePaths;
    }
}

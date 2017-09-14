package com.topcom.cms.web.spring.interceptor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lism on 17-6-13.
 */
@ConfigurationProperties(prefix = "anno",locations = "classpath:auth.properties")
@Configuration
public class AnnoInterceptor {

    private String[] excludePaths;

    public String[] getExcludePaths() {
        return excludePaths;
    }

    public void setExcludePaths(String[] excludePaths) {
        this.excludePaths = excludePaths;
    }
}

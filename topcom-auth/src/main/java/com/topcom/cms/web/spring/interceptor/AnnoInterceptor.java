package com.topcom.cms.web.spring.interceptor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by lism on 17-6-13.
 */
@ConfigurationProperties(prefix = "anno")
@PropertySource("classpath:auth.properties")
//@ConfigurationProperties(prefix = "anno", locations = "classpath:auth.properties")
@Configuration()
public class AnnoInterceptor {

    private Set<String> excludePaths = new LinkedHashSet<>();

    public Set<String> getExcludePaths() {
        return excludePaths;
    }

    public void setExcludePaths(Set<String> excludePaths) {
        this.excludePaths = excludePaths;
    }

    public void addExcludePaths(String... excludePaths) {
        for (String excludePath : excludePaths
                ) {
            this.excludePaths.add(excludePath);
        }
    }

    public void addExcludePath(String excludePath) {
        this.excludePaths.add(excludePath);
    }
}

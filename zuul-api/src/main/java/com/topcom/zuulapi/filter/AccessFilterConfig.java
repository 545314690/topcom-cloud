package com.topcom.zuulapi.filter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
@ConfigurationProperties(prefix = "accessFilter")
public class AccessFilterConfig {
    Set<String> ingorePatterns = new HashSet<>();

    public Set<String> getIngorePatterns() {
        return ingorePatterns;
    }

    public void setIngorePatterns(Set<String> ingorePatterns) {
        this.ingorePatterns = ingorePatterns;
    }
}
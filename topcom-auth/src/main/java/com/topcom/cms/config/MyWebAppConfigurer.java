package com.topcom.cms.config;


import com.topcom.cms.service.ResourceManager;
import com.topcom.cms.web.bind.method.CurrentUserMethodArgumentResolver;
import com.topcom.cms.web.spring.interceptor.AnnoInterceptor;
import com.topcom.cms.web.spring.interceptor.AuthInterceptor;
import com.topcom.cms.web.spring.interceptor.BaseInterceptor;
import com.topcom.cms.web.spring.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

//@Configuration
public class MyWebAppConfigurer
        extends WebMvcConfigurerAdapter {

    @Autowired
    LoginInterceptor loginInterceptor;
    @Autowired
    AnnoInterceptor annoInterceptor;
    @Autowired
    AuthInterceptor authInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new CurrentUserMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedHeaders("*").allowedMethods("*").allowedOrigins("*")
                .allowCredentials(true);
        super.addCorsMappings(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //添加系统文件夹"file:"开头，"/"结尾
        super.addResourceHandlers(registry);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        String[] excludePaths = annoInterceptor.getExcludePaths();
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(excludePaths);
        registry.addInterceptor(authInterceptor).addPathPatterns(authInterceptor.getPaths()).excludePathPatterns(excludePaths);
        super.addInterceptors(registry);
    }
}
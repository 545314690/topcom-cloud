package com.topcom.tjs.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.util.List;

/**
 * @author lism
 */
@Configuration
public class MyWebAppConfigurer
        extends WebMvcConfigurerAdapter {

    /**
     * 跨域请求支持
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedHeaders("*").allowedMethods("*").allowedOrigins("*")
                .allowCredentials(true);
        super.addCorsMappings(registry);
    }

    @Autowired
    private ObjectMapper mapper;

    /**
     * 添加返回结果缩进支持，如果存在pretty参数，则返回结果添加缩进
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.replaceAll(c -> {
            if (c instanceof MappingJackson2HttpMessageConverter) {

                MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper) {
                    @Override
                    protected void writePrefix(JsonGenerator generator, Object object) throws IOException {
                        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
                        if (attributes != null && attributes instanceof ServletRequestAttributes) {
                            String attribute = ((ServletRequestAttributes) attributes).getRequest().getParameter("pretty");
                            if (attribute != null) {
                                generator.setPrettyPrinter(new DefaultPrettyPrinter());
                            }
                        }
                        super.writePrefix(generator, object);
                    }
                };
                return converter;
            } else {
                return c;
            }
        });
    }
}
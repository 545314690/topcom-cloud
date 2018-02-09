package com.topcom.cms.perm.annotation;

import com.topcom.cms.common.utils.LogUtil;
import com.topcom.cms.utils.SpringUtils;
import com.topcom.cms.web.spring.interceptor.AnnoInterceptor;
import org.apache.commons.logging.Log;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

/**
 * 扫描@PublicResource注解，加入匿名拦截器资源排除目录（不拦截的路径）
 * @author lism
 */
@Component
public class PublicResourceProcessor implements BeanPostProcessor {
    private final Log log = LogUtil.logger(PublicResourceProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        /**
         * AnnoInterceptor 通过注入的方式获得的话，会扫描不到注解类（待解决），这里通过SpringUtils获取bean
         */
        AnnoInterceptor annoInterceptor = SpringUtils.getBean(AnnoInterceptor.class);
        PublicResource publicResourceClass = AnnotationUtils.findAnnotation(bean.getClass(), PublicResource.class);
        if (publicResourceClass != null) {
            RequestMapping classRequestMapping = AnnotationUtils.findAnnotation(bean.getClass(), RequestMapping.class);
            if (classRequestMapping != null) {
                String[] paths = classRequestMapping.path();
                for (String path : paths) {
                    String resourcePath = ("/" + path + "/**").replaceAll("//", "/");
                    annoInterceptor.addExcludePath(resourcePath);
                    log.debug("===========public resource======" + resourcePath);
                }
            }
        } else {
            Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
            if (methods != null) {
                for (Method method : methods) {
                    PublicResource resource = AnnotationUtils.findAnnotation(method, PublicResource.class);
                    if (resource != null) {
                        RequestMapping classRequestMapping = AnnotationUtils.findAnnotation(bean.getClass(), RequestMapping.class);
                        RequestMapping methodRequestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
                        String[] classRequestMappingPaths = classRequestMapping.path();
                        String[] methodRequestMappingPaths = methodRequestMapping.path();
                        for (String classRequestMappingPath : classRequestMappingPaths) {
                            for (String methodRequestMappingPath : methodRequestMappingPaths) {
                                String resourcePath = ("/" + classRequestMappingPath + "/" + methodRequestMappingPath).replaceAll("//", "/");
                                annoInterceptor.addExcludePath(resourcePath);
                                log.debug("========public resource======" + resourcePath);
                            }
                        }
                    }
                    // process
                }
            }
        }
        return bean;
    }
}
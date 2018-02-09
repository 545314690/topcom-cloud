package com.topcom.cms.perm.annotation;

import java.lang.annotation.*;

/**
 * Created by lism on 17-12-4.
 * 公开资源注解
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PublicResource {
}

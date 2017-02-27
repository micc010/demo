package com.github.rogerli.framework.annotation;

/**
 * Created by roger on 2017/2/24.
 */

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Roger
 * @create 2017/1/24 9:27
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface LoginAction {
    String value() default "";
}

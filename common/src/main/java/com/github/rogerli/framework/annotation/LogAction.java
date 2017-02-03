/**
 * @文件名称： LogAction.java
 * @文件描述：
 * @版权所有：(C)2017-2028
 * @公司：
 * @完成日期: 2017/1/24
 * @作者 ： Roger
 */
package com.github.rogerli.framework.annotation;

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
public @interface LogAction {
    String value() default "";
}

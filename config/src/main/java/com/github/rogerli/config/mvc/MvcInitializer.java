/**
 * @文件名称： MvcInitializer.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/19
 * @作者 ： Roger
 */
package com.github.rogerli.config.mvc;

import com.github.rogerli.config.security.WebSecurityConfiguration;
import com.github.rogerli.config.session.RedisSessionConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Roger
 * @create 2016/12/19 10:41
 */
public class MvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {WebSecurityConfiguration.class, RedisSessionConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebMvcConfiguration.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
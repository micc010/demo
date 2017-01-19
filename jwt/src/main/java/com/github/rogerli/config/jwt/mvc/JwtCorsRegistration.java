/**
 * @文件名称： JwtCorsRegistration.java
 * @文件描述：
 * @版权所有：(C)2017-2028
 * @公司：
 * @完成日期: 2017/1/20
 * @作者 ： Roger
 */
package com.github.rogerli.config.jwt.mvc;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;

/**
 * @author Roger
 * @create 2017/1/20 0:12
 */
public class JwtCorsRegistration extends CorsRegistration {

    public JwtCorsRegistration(String pathPattern) {
        super(pathPattern);
    }

    @Override
    public CorsConfiguration getCorsConfiguration() {
        return super.getCorsConfiguration();
    }

}

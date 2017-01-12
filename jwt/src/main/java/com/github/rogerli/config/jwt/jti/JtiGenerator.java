/**
 * @文件名称： JtiGenerator.java
 * @文件描述：
 * @版权所有：(C)2017-2028
 * @公司：
 * @完成日期: 2017/1/6
 * @作者 ： Roger
 */
package com.github.rogerli.config.jwt.jti;

import java.util.Date;

/**
 * @author Roger
 * @create 2017/1/6 10:41
 */
public interface JtiGenerator {

    String generateId(Date issuedAt, Date expiration);

}

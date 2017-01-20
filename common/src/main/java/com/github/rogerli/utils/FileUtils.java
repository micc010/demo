/**
 * @文件名称： FileUtils.java
 * @文件描述：
 * @版权所有：(C)2017-2028
 * @公司：
 * @完成日期: 2017/1/20
 * @作者 ： Roger
 */
package com.github.rogerli.utils;

/**
 * @author Roger
 * @create 2017/1/20 16:41
 */
public class FileUtils {

    public static boolean isImage(String fileName){
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return suffix.matches("^(jpg|gif|bmp|png)$");
    }

}

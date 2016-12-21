/**
 * @文件名称： PKList.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/3
 * @作者 ： Roger
 */
package com.github.rogerli.framework.web.model;

import java.util.List;

/**
 * @author Roger
 * @description
 * @create 2016/12/3 15:31
 */
public class PKList<PK> {

    private List<PK> list;

    public List<PK> getList() {
        return list;
    }

    public void setList(List<PK> list) {
        this.list = list;
    }
}
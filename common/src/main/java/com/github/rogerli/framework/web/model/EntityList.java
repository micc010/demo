/**
 * @文件名称： EntityList.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/3
 * @作者 ： Roger
 */
package com.github.rogerli.framework.web.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Roger
 * @description
 * @create 2016/12/3 15:34
 */
public class EntityList<T> implements Serializable {

    private Map<String, Object> map;

    private List<T> list;

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

/**
 * @文件名称： RolePurview.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/5
 * @作者 ： Roger
 */
package com.github.rogerli.system.role.model;

import com.github.rogerli.system.purview.entity.Purview;
import com.github.rogerli.system.role.entity.Role;

import java.util.List;

/**
 * @author Roger
 * @description
 * @create 2016/12/5 11:29
 */
public class RolePurview extends Role{

    private List<Purview> purviewList;

    public List<Purview> getPurviewList() {
        return purviewList;
    }

    public void setPurviewList(List<Purview> purviewList) {
        this.purviewList = purviewList;
    }
}

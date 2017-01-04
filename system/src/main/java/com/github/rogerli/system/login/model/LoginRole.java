/**
 * @文件名称： LoginRole.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/5
 * @作者 ： Roger
 */
package com.github.rogerli.system.login.model;

import com.github.rogerli.system.login.entity.Login;
import com.github.rogerli.system.role.entity.Role;
import com.github.rogerli.system.user.entity.User;

import java.util.List;

/**
 * @author Roger
 * @description
 * @create 2016/12/5 11:29
 */
public class LoginRole extends Login {

    private List<Role> roleList;

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}

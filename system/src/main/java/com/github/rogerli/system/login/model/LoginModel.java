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

import java.util.List;

/**
 * @author Roger
 * @description
 * @create 2016/12/5 11:29
 */
public class LoginModel extends Login {

    private String fullName;

    private String organName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }
}

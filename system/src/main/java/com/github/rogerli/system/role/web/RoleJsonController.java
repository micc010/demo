/**
 * @文件名称： LogJsonController.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/6
 * @作者 ： Roger
 */
package com.github.rogerli.system.role.web;

import com.github.rogerli.framework.service.Service;
import com.github.rogerli.framework.web.AbstractFormJsonController;
import com.github.rogerli.system.role.entity.Role;
import com.github.rogerli.system.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Roger
 * @description
 * @create 2016/12/6 0:03
 */
@Controller
@RequestMapping("/role")
public class RoleJsonController extends AbstractFormJsonController<Role, String> {

    @Autowired
    private RoleService roleService;

    protected Service getService() {
        return roleService;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> hello(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("greeting", "hello world");
        return map;
    }

}

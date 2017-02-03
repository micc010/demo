/**
 * @文件名称： RoleController.java
 * @文件描述：
 * @版权所有：(C)2017-2028
 * @公司：
 * @完成日期: 2017/1/24
 * @作者 ： Roger
 */
package com.github.rogerli.system.role;

import com.github.rogerli.framework.service.Service;
import com.github.rogerli.framework.web.AbstractRestfulController;
import com.github.rogerli.system.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Roger
 * @create 2017/1/24 14:15
 */
@RestController
@RequestMapping("/role")
public class RoleController extends AbstractRestfulController{

    @Autowired
    private RoleService roleService;

    @Override
    protected Service getService() {
        return roleService;
    }

}

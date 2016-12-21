/**
 * @文件名称： LoginMapperTest.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/11/30
 * @作者 ： Roger
 */
package com.github.rogerli.system.user;

import com.github.pagehelper.PageHelper;
import com.github.rogerli.Application;
import com.github.rogerli.system.role.entity.Role;
import com.github.rogerli.system.user.dao.UserMapper;
import com.github.rogerli.system.user.entity.User;
import com.github.rogerli.system.user.model.UserRole;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * @author Roger
 * @description
 * @create 2016/11/30 19:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * @return
     */
    @Ignore
    @Test
    public void test1selectUserRole() {
        User query = new User();
        query.setId("1");
        PageHelper.startPage(1,1);
        List<Role> list = userMapper.selectRoleList(query);
        System.out.println(list);
    }

    /**
     * @return
     */
    @Ignore
    @Test
    public void test2selectRoleList() {
        User query = new User();
        query.setId("1");
        PageHelper.startPage(1,1);
        UserRole role = userMapper.selectUserRole(query);
        System.out.println(role);
    }

}

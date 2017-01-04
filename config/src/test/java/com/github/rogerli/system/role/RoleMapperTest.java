/**
 * @文件名称： LoginMapperTest.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/11/30
 * @作者 ： Roger
 */
package com.github.rogerli.system.role;

import com.github.pagehelper.PageHelper;
import com.github.rogerli.system.purview.entity.Purview;
import com.github.rogerli.system.role.dao.RoleMapper;
import com.github.rogerli.system.role.entity.Role;
import com.github.rogerli.system.role.model.RolePurview;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

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
public class RoleMapperTest {

    @Autowired
    private RoleMapper roleMapper;

    @Ignore
    @Test
    public void test1() {
        Role role = new Role();
        role.setId("1");
        role.setRole("Admin");
        role.setOrganId("1");
        role.setRoleName("超级管理员");
        role.setIsAdmin(1);
        role.setAvailable(1);
        role.setDescriptions("11");
        roleMapper.insert(role);
    }

//    @Ignore
    @Test
    public void test2() {
        Role role = roleMapper.selectByPrimaryKey("1");
        Assert.isTrue(role.getId().equals("1"));
    }

    @Ignore
    @Test
    public void test3() {
        Role role = roleMapper.selectByPrimaryKey("1");
        PageHelper.startPage(1, 1);
        List<Purview> list = roleMapper.selectPurviewList(role);
        System.out.println(list);
    }

    @Ignore
    @Test
    public void test4() {
        Role role = new Role();
        role.setId("1");
        PageHelper.startPage(1, 1);
        RolePurview rolePurview = roleMapper.selectRolePurview(role);
        System.out.println(rolePurview);
    }

}

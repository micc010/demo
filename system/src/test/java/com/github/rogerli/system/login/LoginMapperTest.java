/**
 * @文件名称： LoginMapperTest.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/11/30
 * @作者 ： Roger
 */
package com.github.rogerli.system.login;

import com.github.pagehelper.PageHelper;
import com.github.rogerli.ApplicationTest;
import com.github.rogerli.system.login.dao.LoginMapper;
import com.github.rogerli.system.login.entity.Login;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
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
@SpringApplicationConfiguration(classes = ApplicationTest.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginMapperTest {

    @Autowired
    private LoginMapper loginMapper;

//    /**
//     * @return
//     */
//    @Test
//    public void test1insert() {
//        Login login = new Login();
//        login.setId("1");
//        login.setLocked(1);
//        login.setPassword("11");
//        login.setSalt("11");
//        login.setUserName("lala");
//        login.setUserId("1");
//        int i = loginMapper.insert(login);
//        Assert.isTrue(1 == i);
//    }

    /**
     * @return
     */
    @Test
    public void test2delete() {
        int i = loginMapper.deleteByPrimaryKey("2");
        Assert.isTrue(1 == i);
    }

    /**
     * @return
     */
    @Test
    public void test3insertSelective() {
        Login login = new Login();
        login.setId("2");
        login.setLocked(0);
        login.setPassword("22");
        login.setSalt("22");
        login.setUserName("hahalala");
        login.setUserId("2");
        int i = loginMapper.insert(login);
        Assert.isTrue(1 == i);
    }

//    /**
//     * @return
//     */
//    @Test
//    public void test4selectByPrimaryKey() {
//        Login login = loginMapper.selectByPrimaryKey("2");
//        Assert.notNull(login);
//    }
//
//    /**
//     * @return
//     */
//    @Test
//    public void test5updateByPrimaryKey() {
//        Login login = loginMapper.selectByPrimaryKey("2");
//        System.out.println(login.getUserName());
//        login.setUserName("3333");
//        loginMapper.updateByPrimaryKey(login);
//    }
//
//    /**
//     * @return
//     */
//    @Test
//    public void test6updateByPrimaryKeySelective() {
//        Login login = loginMapper.selectByPrimaryKey("2");
//        System.out.println(login.getUserName());
//        login.setUserName("4444");
//        loginMapper.updateByPrimaryKeySelective(login);
//    }

    /**
     * @return
     */
    @Test
    public void test7selectList() {
        Login login = loginMapper.selectByPrimaryKey("2");
        List<Login> list = loginMapper.selectList(login);
        Assert.notEmpty(list);
    }

//    /**
//     * @return
//     */
//    @Test
//    public void test8selectPage() {
//        Login login = new Login();
//        PageHelper.startPage(2,1);
//        List<Login> page = loginMapper.selectList(login);
//        Assert.notNull(page);
//    }
//
//    /**
//     * @return
//     */
//    @Test
//    public void test9delete() {
//        int i = loginMapper.deleteByPrimaryKey("1");
//        Assert.isTrue(1 == i);
//        i = loginMapper.deleteByPrimaryKey("2");
//        Assert.isTrue(1 == i);
//    }

}

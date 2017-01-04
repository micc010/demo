/**
 * @文件名称： LoginMapperTest.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/11/30
 * @作者 ： Roger
 */
package com.github.rogerli.system.login;

import com.github.rogerli.system.login.dao.LoginMapper;
import com.github.rogerli.system.login.entity.Login;
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

/**
 * @author Roger
 * @description
 * @create 2016/11/30 19:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginMapperTest {

    @Autowired
    private LoginMapper loginMapper;

    @Ignore
    @Test
    public void test3() {
        Login login = loginMapper.selectByUsername("lala");
        Assert.notNull(login);
    }

}

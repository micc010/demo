/**
 * @文件名称： PasswordTest.java
 * @文件描述：
 * @版权所有：(C)2017-2028
 * @公司：
 * @完成日期: 2017/1/4
 * @作者 ： Roger
 */
package com.github.rogerli.system;

import com.github.rogerli.ApplicationTest;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Roger
 * @create 2017/1/4 13:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PasswordTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
//    @Ignore
    public void test3() {
        System.out.println(passwordEncoder.encode("123456"));
    }
}

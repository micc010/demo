/**
 * @文件名称： LoginService.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/1
 * @作者 ： Roger
 */
package com.github.rogerli.system.login.service;

import com.github.rogerli.framework.service.AbstractService;
import com.github.rogerli.system.login.dao.LoginMapper;
import com.github.rogerli.system.login.entity.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Roger
 * @description
 * @create 2016/12/1 0:47
 */
@Service
public class LoginService extends AbstractService<Login, LoginMapper>{

    private static final Logger LOGGER = LoggerFactory.getLogger(Login.class);

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public LoginMapper getMapper() {
        return loginMapper;
    }

}

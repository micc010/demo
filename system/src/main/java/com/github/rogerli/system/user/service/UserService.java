/**
 * @文件名称： LoginService.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/1
 * @作者 ： Roger
 */
package com.github.rogerli.system.user.service;

import com.github.rogerli.framework.service.AbstractService;
import com.github.rogerli.system.user.dao.UserMapper;
import com.github.rogerli.system.user.entity.User;
import com.github.rogerli.system.user.entity.UserMeModel;
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
public class UserService extends AbstractService<User, String, UserMapper>{

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserMapper getMapper() {
        return userMapper;
    }

    public UserMeModel findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}

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
import com.github.rogerli.system.login.model.LoginRole;
import com.github.rogerli.system.purview.entity.Purview;
import com.github.rogerli.system.role.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author Roger
 * @description
 * @create 2016/12/1 0:47
 */
@Service
public class LoginService extends AbstractService<Login, String, LoginMapper> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Login.class);

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public LoginMapper getMapper() {
        return loginMapper;
    }

    /**
     * @param userName
     * @return
     */
    public Login findByUsername(String userName) {
        return getMapper().findByUsername(userName);
    }

    public Optional<LoginRole> findRoleByUsername(String userName) {
        return Optional.of(getMapper().findRoleByUsername(userName));
    }

    /**
     *
     * @param query
     * @return
     */
    public List<Role> findRoleList(Login query) {
        return getMapper().findRoleList(query);
    }

    /**
     *
     * @param query
     * @return
     */
    public List<Purview> findUserPurview(Login query){
        return getMapper().findUserPurview(query);
    }

    /**
     *
     * @param query
     * @return
     */
    public LoginRole findUserRole(Login query){
        return getMapper().findUserRole(query);
    }

    /**
     *
     * @param user
     * @return
     */
    public Login saveUser(Login user){
        Assert.notNull(user);
        user.setPassword(encoder.encode(user.getPassword()));
        if (StringUtils.hasText(user.getId())){
            getMapper().updateByKey(user);
        } else {
            getMapper().insert(user);
        }
        return user;
    }

}

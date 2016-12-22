package com.github.rogerli.system.login.dao;

import com.github.rogerli.framework.dao.Mapper;
import com.github.rogerli.system.login.entity.Login;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper extends Mapper<Login, String> {

    Login selectByUsername(String userName);

}
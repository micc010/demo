package com.github.rogerli.system.user.dao;

import com.github.rogerli.framework.dao.Mapper;
import com.github.rogerli.system.user.entity.User;
import com.github.rogerli.system.user.entity.UserMeModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends Mapper<User, String> {

    UserMeModel findByUsername(String username);
}
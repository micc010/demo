package com.github.rogerli.system.login.dao;

import com.github.rogerli.framework.dao.Mapper;
import com.github.rogerli.system.login.entity.Login;
import com.github.rogerli.system.login.model.LoginRole;
import com.github.rogerli.system.purview.entity.Purview;
import com.github.rogerli.system.role.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginMapper extends Mapper<Login, String> {

    Login selectByUsername(String userName);

    LoginRole selectRoleByUsername(String userName);

    List<Role> selectRoleList(Login query);

    List<Purview> selectUserPurview(Login query);

    LoginRole selectUserRole(Login query);

}
package com.github.rogerli.system.role.dao;

import com.github.rogerli.framework.dao.Mapper;
import com.github.rogerli.system.login.entity.Login;
import com.github.rogerli.system.purview.entity.Purview;
import com.github.rogerli.system.role.entity.Role;
import com.github.rogerli.system.role.model.RolePurview;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends Mapper<Role, String> {

    List<Purview> selectPurviewList(Role query);

    RolePurview selectRolePurview(Role query);

    List<Role> selectRoleListByPurview(Purview query);

    List<Role> selectRoleListByLogin(Login query);

}
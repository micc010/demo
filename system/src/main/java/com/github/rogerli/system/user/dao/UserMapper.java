package com.github.rogerli.system.user.dao;

import com.github.rogerli.framework.dao.Mapper;
import com.github.rogerli.system.purview.entity.Purview;
import com.github.rogerli.system.role.entity.Role;
import com.github.rogerli.system.user.entity.User;
import com.github.rogerli.system.user.model.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends Mapper<User, String> {

    List<Role> selectRoleList(User query);

    List<Purview> selectUserPurview(User query);

    UserRole selectUserRole(User query);

}
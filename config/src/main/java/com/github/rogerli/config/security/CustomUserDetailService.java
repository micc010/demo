/**
 * @文件名称： CustomUserDetailService.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/8
 * @作者 ： Roger
 */
package com.github.rogerli.config.security;

import com.github.rogerli.system.login.entity.Login;
import com.github.rogerli.system.login.service.LoginService;
import com.github.rogerli.system.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 根据用户查找权限
 *
 * @author Roger
 * @date 2016/12/8 11:12
 */
@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("用户名为空");
        }

        Login login = loginService.selectByUsername(username);
        if (login == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        roleService.selectRoleListByLogin(login).forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getRole())));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return new CustomUserDetails(username, encoder.encode(login.getPassword()), authorities);
    }
}

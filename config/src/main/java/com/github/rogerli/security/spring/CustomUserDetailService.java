/**
 * @文件名称： CustomUserDetailService.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/8
 * @作者 ： Roger
 */
package com.github.rogerli.security.spring;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        if (StringUtils.isEmpty(username)) {
//            throw new UsernameNotFoundException("用户名为空");
//        }
//
//        Login login = loginService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
//
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        roleService.getRoles(login.getId()).forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getName())));
//
//        return new org.springframework.security.core.userdetails.User(
//                username, login.getPassword(),
//                true,//是否可用
//                true,//是否过期
//                true,//证书不过期为true
//                true,//账户未锁定为true
//                authorities);

        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        grantedAuthoritySet.add(grantedAuthority);
        CustomUserDetails userDetails = new CustomUserDetails(username, "password", grantedAuthoritySet);
        return userDetails;
    }
}

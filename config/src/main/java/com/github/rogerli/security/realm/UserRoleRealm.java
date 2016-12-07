package com.github.rogerli.security.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author roger.li
 */
public class UserRoleRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

//        String username = (String) principals.getPrimaryPrincipal();
//
//        Set<Role> roleSet = userService.findUserByUsername(username).getRoleSet();
//        //角色名的集合
//        Set<String> roles = new HashSet<String>();
//        //权限名的集合
//        Set<String> permissions = new HashSet<String>();
//
//        Iterator<Role> it = roleSet.iterator();
//        while (it.hasNext()) {
//            roles.add(it.next().getName());
//            for (Permission per : it.next().getPermissionSet()) {
//                permissions.add(per.getName());
//            }
//        }
//
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//
//        authorizationInfo.addRoles(roles);
//        authorizationInfo.addStringPermissions(permissions);
//
//
//        return authorizationInfo;

        return null;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

//        String username = (String) token.getPrincipal();
//        User user = userService.findUserByUsername(username);
//
//        if (user == null) {
//            //木有找到用户
//            throw new UnknownAccountException("没有找到该账号");
//        }
//            /* if(Boolean.TRUE.equals(user.getLocked())) {
//              throw new LockedAccountException(); //帐号锁定
//          } */
//
//        /**
//         * 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
//         */
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), user.getSalt(), getName());
//
//        return info;
        return null;
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

}

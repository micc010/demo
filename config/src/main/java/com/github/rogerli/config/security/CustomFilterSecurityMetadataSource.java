package com.github.rogerli.config.security;

//import com.github.rogerli.config.restful.ValidationToken;
//import com.github.rogerli.config.restful.ValidationTokenService;

import com.github.rogerli.system.purview.entity.Purview;
import com.github.rogerli.system.purview.service.PurviewService;
import com.github.rogerli.system.role.entity.Role;
import com.github.rogerli.system.role.model.RolePurview;
import com.github.rogerli.system.role.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 加载权限对应的角色
 *
 * @author roger.li
 */
@Component
public class CustomFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final Logger LOGGER = LoggerFactory
            .getLogger(CustomFilterSecurityMetadataSource.class);

//    @Autowired
//    private ValidationTokenService validationTokenService;

    @Autowired
    private PurviewService purviewService;

    @Autowired
    private RoleService roleService;

    /**
     * 资源所需要的权限
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        System.out.println("Full request URL: " + fi.getFullRequestUrl());
        System.out.println("Request URL: " + fi.getRequestUrl());
        System.out.println("HTTP Method: " + fi.getRequest().getMethod());
        System.out.println("Context path: " + fi.getRequest().getContextPath());

        Collection<ConfigAttribute> securityConfigList = new ArrayList<ConfigAttribute>();
        //在Resource表找到该资源对应的角色
        Purview query = new Purview();
        query.setUrl(fi.getRequestUrl());
        List<Role> roleList = roleService.selectRoleListByPurview(query);
        if(roleList == null || roleList.size() == 0){
            //若数据库没对该资源做配置，则至少要是登录的角色
            LOGGER.info("url " + fi.getRequestUrl() + " need role USER");
            SecurityConfig securityConfig = new SecurityConfig("USER");
            securityConfigList.add(securityConfig);
            return securityConfigList;
        }
        for (Role role :
                roleList) {
            //以角色名称来存放
            SecurityConfig securityConfig = new SecurityConfig(role.getRole());
            securityConfigList.add(securityConfig);
            LOGGER.info("url need role " + role.getRole());
        }
        return securityConfigList;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }

}

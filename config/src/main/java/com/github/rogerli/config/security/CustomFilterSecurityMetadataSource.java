package com.github.rogerli.config.security;

//import com.github.rogerli.config.restful.ValidationToken;
//import com.github.rogerli.config.restful.ValidationTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        List<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>(0);
        // Lookup your database (or other source) using this information and populate the
        // list of attributes

        SecurityConfig securityConfig = new SecurityConfig("USER");

        configAttributes.add(securityConfig);

        return configAttributes;

//        FilterInvocation filterInvocation = (FilterInvocation) object;
//        String url = filterInvocation.getRequestUrl();
//        LOGGER.debug("Request url " + url);
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("url", url);
//
//        try {
//            Collection<ConfigAttribute> securityConfigList = new ArrayList<ConfigAttribute>();
//            //在Resource表找到该资源
//            SysResource sysResource = sysResourceService.selectOneByMap(param);
//            param.clear();
//            if (sysResource == null) {
//                //若数据库没对该资源做配置，则要登录角色
//                LOGGER.debug("Url " + url + " Need Role USER");
//                SecurityConfig securityConfig = new SecurityConfig("USER");
//                securityConfigList.add(securityConfig);
//                return securityConfigList;
//            }
//            //找到该资源对应的角色
//            param.put("resourceId", sysResource.getId());
//
//            List<SysRoleResource> sysRoleResourceList = sysRoleResourceService.selectListByMap(param);
//
//            for (int i = 0; i < sysRoleResourceList.size(); i++) {
//                //找到角色
//                SysRole sysRole = sysRoleService.selectByPrimaryKey(sysRoleResourceList.get(i).getRoleId());
//                if (sysRole != null) {
//                    //以角色名称来存放
//                    SecurityConfig securityConfig = new SecurityConfig(sysRole.getName());
//                    securityConfigList.add(securityConfig);
//                    LOGGER.info("Url need role " + sysRole.getName());
//                }
//            }
//            return securityConfigList;
//        } catch (ServiceException serviceException) {
//            return null;
//        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }

}

package com.github.rogerli.config.shiro;

import com.github.rogerli.config.datasource.DruidDataSourceConfiguration;
import com.github.rogerli.config.mybatis.MybatisConfiguration;
import com.github.rogerli.security.matcher.RetryLimitHashedCredentialsMatcher;
import com.github.rogerli.security.session.eis.RedisSessionDAO;
import com.github.rogerli.config.redis.RedisCacheConfiguration;
import com.github.rogerli.security.realm.UserRoleRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * @author roger.li
 */
//@Configuration
//@AutoConfigureBefore({DruidDataSourceConfiguration.class, MybatisConfiguration.class})
//@AutoConfigureAfter({RedisCacheConfiguration.class})
public class ShiroConfiguration {

//    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroConfiguration.class);
//
//    /**
//     * 配置securityManager
//     *
//     * @param sessionManager
//     * @param rememberMeManager
//     * @param cacheManager
//     * @param realm
//     * @return
//     */
//    @Bean
//    public DefaultWebSecurityManager securityManager(SessionManager sessionManager,
//                                                     RememberMeManager rememberMeManager,
//                                                     CacheManager cacheManager,
//                                                     UserRoleRealm realm) {
//        LOGGER.debug("======Setting security manager======");
//        final DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setSessionManager(sessionManager);
//        securityManager.setCacheManager(cacheManager); // 配置cacheManager
//        securityManager.setRememberMeManager(rememberMeManager);
//        securityManager.setRealm(realm);
//        return securityManager;
//    }
//
//    /**
//     * @param credentialsMatcher
//     * @return
//     */
//    @Bean
//    public UserRoleRealm realm(CredentialsMatcher credentialsMatcher) {
//        LOGGER.debug("======Setting realm======");
//        UserRoleRealm realm = new UserRoleRealm();
//        realm.setCredentialsMatcher(credentialsMatcher);
//        realm.setCachingEnabled(false);
//        return realm;
//    }
//
//    /**
//     * 重复次数
//     *
//     * @param cacheManager
//     * @return
//     */
//    @Bean
//    public CredentialsMatcher credentialsMatcher(RedisShiroCacheManager cacheManager) {
//        LOGGER.debug("======Setting RetryLimitHashedCredentialsMatcher======");
//        RetryLimitHashedCredentialsMatcher hashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher(cacheManager);
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
//        hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于md5(md5(""));
//        return hashedCredentialsMatcher;
//    }
//
//    /**
//     * 配置shiroFilter
//     * 对应配置文件中的<code>org.apache.shiro.spring.web.ShiroFilterFactoryBean</code>类
//     *
//     * @param securityManager
//     * @return
//     */
//    @Bean
//    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
//        ShiroConfiguration.LOGGER.debug("======Setting Shiro DefaultFilter======");
//        final ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
//        factoryBean.setSecurityManager(securityManager);
//        factoryBean.setLoginUrl("/login");
//        factoryBean.setSuccessUrl("/index");
//        factoryBean.setUnauthorizedUrl("/error");
//
//        final LinkedHashMap<String, String> filterChains = new LinkedHashMap<>();
//        String anon = "anon";
//
//        // 静态资源
////        filterChains.put("${spring.mvc.static-path-pattern}", anon);
//        filterChains.put("/**", anon);
//
////        filterChains.put("/static/js/**", anon);
////        filterChains.put("/static/images/**", anon);
////        filterChains.put("/static/css/**", anon);
//
//        // shiro 设置哪些可以使用druid监控
////        filterChains.put("/druid/**", anon);
//
//        ShiroConfiguration.LOGGER.debug("Creating filter chain {}", filterChains);
//        factoryBean.setFilterChainDefinitionMap(filterChains);
//        return factoryBean;
//    }
//
//    /**
//     * shiro会话session配置
//     */
//    @Configuration
//    protected static class ShiroSessionConfiguration {
//
//        @Autowired(required = false)
//        Collection<SessionListener> sessionListeners;
//
//        /**
//         * Could not inject {@code RedisTemplate<Serializable, Session>}
//         */
//        @Autowired
//        private RedisTemplate redisTemplate;
//
//        /**
//         * 定义sessionDao
//         *
//         * @return
//         */
//        @Bean
//        public SessionDAO sessionDAO() {
//            LOGGER.debug("======Setting session DAO======");
//            return new RedisSessionDAO(redisTemplate);
//        }
//
//        /**
//         * 配置sessionManager
//         * 对应配置文件中的<code>org.apache.shiro.web.session.mgt.DefaultWebSessionManager</code>
//         *
//         * @param sessionCookie
//         * @return
//         */
//        @Bean
//        public DefaultWebSessionManager sessionManager(@Qualifier("sessionCookie") SimpleCookie sessionCookie) {
//            LOGGER.debug("======Creating session manager======");
//            final DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//            sessionManager.setGlobalSessionTimeout(20 * 1000); // 全局会话过期时间
//            sessionManager.setDeleteInvalidSessions(true); // 删除过期会话
//            sessionManager.setSessionValidationSchedulerEnabled(false); // 关闭会话验证，交由redis进行会话超期管理
//            sessionManager.setSessionDAO(sessionDAO()); // 设置redis会话dao
//            // Listeners for create, stop or expiration
//            if (sessionListeners != null && !sessionListeners.isEmpty()) {
//                sessionManager.setSessionListeners(sessionListeners);
//            }
//
//            // commonly set for change session cookies name (default JSESSIOINID)
//            sessionManager.setSessionIdCookie(sessionCookie);
//            return sessionManager;
//        }
//    }
//
//    /**
//     * shiro 会话Cookie模板
//     */
//    @Configuration
//    protected static class ShiroCookieConfiguration {
//
//        /**
//         * 配置sessionIdCookie
//         * 对应配置文件中的<code>org.apache.shiro.web.servlet.SimpleCookie</code>
//         *
//         * @return
//         */
//        @Bean(name = "sessionCookie")
//        public SimpleCookie sessionCookie() {
//            LOGGER.debug("======Creating session cookie bean======");
//            final SimpleCookie sessionCookie = new SimpleCookie("sessionCookie");
//            sessionCookie.setPath("/");
//            sessionCookie.setHttpOnly(true);
//            sessionCookie.setMaxAge(-1); // 浏览器关闭时失效此Cookie
//            return sessionCookie;
//        }
//
//        /**
//         * 定义rememberCookie
//         *
//         * @return
//         */
//        @Bean
//        public SimpleCookie rememberCookie() {
//            LOGGER.debug("======Creating remember cookie bean======");
//            final SimpleCookie rememberCookie = new SimpleCookie("rememberCookie");
//            rememberCookie.setPath("/");
//            rememberCookie.setHttpOnly(true);
//            rememberCookie.setMaxAge(7 * 24 * 60 * 60); // 最大时间一周
//            return rememberCookie;
//        }
//
//        /**
//         * 定义rememberMeManager
//         *
//         * @param rememberCookie
//         * @return
//         */
//        @Bean
//        public RememberMeManager rememberMeManager(@Qualifier("rememberCookie") SimpleCookie rememberCookie) {
//            LOGGER.debug("======Creating remember manager======");
//            final CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
//            rememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
//            rememberMeManager.setCookie(rememberCookie);
//            return rememberMeManager;
//        }
//    }
//
//    /**
//     *
//     */
//    @Configuration
//    protected static class Processor {
//
//        /**
//         * lifecycleBeanPostProcessor 生命周期
//         * <code>org.apache.shiro.spring.LifecycleBeanPostProcessor</code>
//         *
//         * @return
//         */
//        @Bean
//        public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//            return new LifecycleBeanPostProcessor();
//        }
//
//        /**
//         * 代理<code>org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator</code>
//         * TODO 是不是会冲突呢？
//         *
//         * @return
//         */
//        @Bean
//        public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//            final DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
//            proxyCreator.setProxyTargetClass(true);
//            return proxyCreator;
//        }
//
//        @Bean
//        public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
//            final AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//            sourceAdvisor.setSecurityManager(defaultWebSecurityManager);
//            return sourceAdvisor;
//        }
//    }

}

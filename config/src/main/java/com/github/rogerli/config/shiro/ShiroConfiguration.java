package com.github.rogerli.config.shiro;

import com.github.rogerli.config.datasource.DruidDataSourceConfiguration;
import com.github.rogerli.config.mybatis.MybatisConfiguration;
import com.github.rogerli.security.session.eis.RedisSessionDAO;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
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
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * @author roger.li
 */
@Configuration
@AutoConfigureBefore({DruidDataSourceConfiguration.class, MybatisConfiguration.class})
public class ShiroConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroConfiguration.class);

    /**
     * 配置securityManager
     *
     * @param sessionManager
     * @param rememberMeManager
     * @param realms
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(SessionManager sessionManager,
                                                     RememberMeManager rememberMeManager,
                                                     Collection<Realm> realms) {
        LOGGER.debug("Setting security manager");
        final DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);

        // TODO cache manager here

        securityManager.setRememberMeManager(rememberMeManager);
        securityManager.setRealms(realms);
        return securityManager;
    }

    /**
     * 配置shiroFilter
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroConfiguration.LOGGER.debug("Setting Shiro DefaultFilter");
        final ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/login");

        // TODO 这里可以动态

        final LinkedHashMap<String, String> filterChains = new LinkedHashMap<>();
        filterChains.put("/static/**", DefaultFilter.ANON.filterName());
        filterChains.put("/public/**", DefaultFilter.ANON.filterName());
        filterChains.put("/js/**", DefaultFilter.ANON.filterName());
        filterChains.put("/css/**", DefaultFilter.ANON.filterName());
        filterChains.put("/druid/**", DefaultFilter.USER.filterName());

        ShiroConfiguration.LOGGER.debug("Creating filter chain {}", filterChains);
        factoryBean.setFilterChainDefinitionMap(filterChains);
        return factoryBean;
    }

    /**
     * shiro会话session配置
     */
    @Configuration
    protected static class ShiroSessionConfiguration {
        @Autowired(required = false)
        Collection<SessionListener> sessionListeners;

        /**
         * Could not inject {@code RedisTemplate<Serializable, Session>}
         */
        @Autowired
        private RedisTemplate redisTemplate;

        /**
         * 定义sessionDao
         *
         * @return
         */
        @Bean
        public SessionDAO sessionDAO() {
            LOGGER.debug("Setting session DAO");
            return new RedisSessionDAO(redisTemplate);
        }

        /**
         * 定义sessionManager
         *
         * @param sessionCookie
         * @return
         */
        @Bean
        public DefaultWebSessionManager sessionManager(@Qualifier("sessionCookie") SimpleCookie sessionCookie) {
            LOGGER.debug("Creating session manager");
            final DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
            // Session expire time in mill unit
            sessionManager.setGlobalSessionTimeout(20 * 1000);
            // Redis Recommend
            sessionManager.setSessionDAO(sessionDAO());
            // Listeners for create, stop or expiration
            if (sessionListeners != null && !sessionListeners.isEmpty()) {
                sessionManager.setSessionListeners(sessionListeners);
            }
            // Turn off session validation cause redis has controlled the expire of session
            sessionManager.setSessionValidationSchedulerEnabled(false);
            // commonly set for change session cookies name (default JSESSIOINID)
            sessionManager.setSessionIdCookie(sessionCookie);
            return sessionManager;
        }
    }

    /**
     * shiro cookie配置
     */
    @Configuration
    protected static class ShiroCookieConfiguration {

        /**
         * 定义sessionCookie
         *
         * @return
         */
        @Bean(name = "sessionCookie")
        public SimpleCookie sessionCookie() {
            LOGGER.debug("Creating session cookie bean");
            final SimpleCookie sessionCookie = new SimpleCookie("sessionCookie");
            sessionCookie.setPath("/");
            sessionCookie.setHttpOnly(true);
            sessionCookie.setMaxAge(-1);
            return sessionCookie;
        }

        /**
         * 定义rememberCookie
         *
         * @return
         */
        @Bean
        public SimpleCookie rememberCookie() {
            LOGGER.debug("Creating remember cookie bean");
            final SimpleCookie rememberCookie = new SimpleCookie("rememberCookie");
            rememberCookie.setPath("/");
            rememberCookie.setHttpOnly(true);
            // second unit
            rememberCookie.setMaxAge(7 * 24 * 60 * 60);
            return rememberCookie;
        }

        /**
         * 定义rememberMeManager
         *
         * @param rememberCookie
         * @return
         */
        @Bean
        public RememberMeManager rememberMeManager(@Qualifier("rememberCookie") SimpleCookie rememberCookie) {
            LOGGER.debug("Creating remember manager");
            final CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
            rememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
            rememberMeManager.setCookie(rememberCookie);
            return rememberMeManager;
        }
    }

    @Configuration
    protected static class Processor {

        @Bean
        public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
            return new LifecycleBeanPostProcessor();
        }

        @Bean
        public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
            final DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
            proxyCreator.setProxyTargetClass(true);
            return proxyCreator;
        }
    }

    // roles, perms, port, rest are NOT support
    enum DefaultFilter {

        SSL("ssl"),
        ANON("anon"),
        AUTHC("authc"),
        AUTHCBASIC("authcBasic"),
        LOGOUT("logout"),
        USER("user"),
        NO_SESSION("noSessionCreation");

        private final String name;

        private DefaultFilter(String name) {
            this.name = name;
        }

        public String filterName() {
            return this.name;
        }


    }

}

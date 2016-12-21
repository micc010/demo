package com.github.rogerli.config.security;

import com.github.rogerli.config.restful.RestfulUsernamePasswordAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * AuthenticationManager 管理认证
 * AuthenticationProvider 改变认证方式
 * UserDetailsService 改变认证的用户信息来源
 * ProviderManager 清除返回的Authentication中的凭证信息，如密码
 * AccessDecisionManager
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfiguration.class);

    @Autowired
    private CustomUserDetailService userDetailService;

    @Resource
    private CustomFilterSecurityMetadataSource securityMetadataSource;

    /**
     * Web层面的配置，一般用来配置无需安全检查的路径
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        LOGGER.debug("======WebSecurity configure======");
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/fonts/**");
    }

    /**
     * Request层面的配置，对应XML Configuration中的<http>元素
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.debug("======HttpSecurity configure======");

        http.csrf().disable(); // 关闭csrf

        http.authorizeRequests()
                .mvcMatchers("/", "/index", "/login?invalid").permitAll();// 一般请求

        http.authorizeRequests()
                .antMatchers(RestfulUsernamePasswordAuthenticationFilter.SPRING_SECURITY_RESTFUL_LOGIN_URL).permitAll();// restful请求

//        // restful 请求
//        http.addFilterAfter(createRestfulUsernamePassswordAuthenticationFilter(authenticationManager()),
//                UsernamePasswordAuthenticationFilter.class)
//                .authorizeRequests().regexMatchers("^/api.*$").authenticated()
//                .and()
//                .formLogin().loginPage(RestfulUsernamePasswordAuthenticationFilter.SPRING_SECURITY_RESTFUL_LOGIN_URL)
//                .and()
//                .logout()
//                .logoutSuccessHandler(new RestfulLogoutSuccessHandler()).permitAll()
//                .invalidateHttpSession(true)
//                .and()
//                .exceptionHandling().authenticationEntryPoint(new RestfulAuthenticationEntryPoint())
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    public <O extends FilterSecurityInterceptor> O postProcess(
//                            O fsi) {
//                        fsi.setSecurityMetadataSource(securityMetadataSource);
//                        fsi.setAccessDecisionManager(accessDecisionManager());
//                        fsi.setAuthenticationManager(authenticationManagerBean());
//                        return fsi;
//                    }
//                })
//                .and()
//                .authorizeRequests().expressionHandler(webSecurityExpressionHandler())
//                .and()
//                .sessionManagement().maximumSessions(1); // session管理

        // 一般请求
        http.authorizeRequests().regexMatchers("^(?!/api).*$").authenticated()
//        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/home").failureUrl("/login?error").permitAll()
                .and()
                .logout().logoutSuccessUrl("/login?logout").permitAll()
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling().accessDeniedPage("/access-denie.html")
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    public <O extends FilterSecurityInterceptor> O postProcess(
                            O fsi) {
                        fsi.setSecurityMetadataSource(securityMetadataSource);
                        fsi.setAccessDecisionManager(accessDecisionManager());
                        fsi.setAuthenticationManager(authenticationManagerBean());
                        return fsi;
                    }
                })
                .and()
                .authorizeRequests().expressionHandler(webSecurityExpressionHandler())
                .and()
                .sessionManagement().invalidSessionUrl("/login?invalid").maximumSessions(1); // session管理

    }

//    public Filter createRestfulUsernamePassswordAuthenticationFilter(AuthenticationManager manager) {
//        RestfulUsernamePasswordAuthenticationFilter filter = new RestfulUsernamePasswordAuthenticationFilter();
//        filter.setAuthenticationManager(manager);
//        filter.setAuthenticationFailureHandler(new RestfulAuthenticationFailureHandler());
//        filter.setAuthenticationSuccessHandler(new RestfulAuthenticationSuccessHandler());
//        filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(
//                RestfulUsernamePasswordAuthenticationFilter.SPRING_SECURITY_RESTFUL_LOGIN_URL, "POST"));
//        return filter;
//    }

    /**
     * 身份验证配置，用于注入自定义身份验证Bean和密码校验规则
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        LOGGER.debug("======AuthenticationManagerBuilder configure======");
        // 注入bean
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        auth.eraseCredentials(true).userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }

    /**
     * 登录失败
     *
     * @return
     */
    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        LOGGER.debug("======accessDeniedHandler======");
        AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
        accessDeniedHandler.setErrorPage("/access-denie.html");
        return accessDeniedHandler;
    }

    /**
     * 登录事件
     *
     * @return
     */
    @Bean
    public org.springframework.security.authentication.event.LoggerListener authenticationLoggerListener() {
        LOGGER.debug("======authenticationLoggerListener======");
        return new org.springframework.security.authentication.event.LoggerListener();
    }

    /**
     * 通过事件
     *
     * @return
     */
    @Bean
    public org.springframework.security.access.event.LoggerListener accessEventLoggerListener() {
        LOGGER.debug("======accessEventLoggerListener======");
        return new org.springframework.security.access.event.LoggerListener();
    }

    /**
     * 访问决策器
     *
     * @return
     */
    @Bean(name = "accessDecisionManager")
    public AccessDecisionManager accessDecisionManager() {
        LOGGER.debug("======accessDecisionManager======");
        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList();
        decisionVoters.add(new RoleVoter());
        decisionVoters.add(new AuthenticatedVoter());
        decisionVoters.add(webExpressionVoter());// 启用表达式投票器
        CustomAccessDecisionManager accessDecisionManager = new CustomAccessDecisionManager(decisionVoters);
        return accessDecisionManager;
    }


    /**
     * 认证管理器
     *
     * @return
     */
    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() {
        LOGGER.debug("======authenticationManagerBean======");
        AuthenticationManager authenticationManager = null;
        try {
            authenticationManager = super.authenticationManagerBean();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticationManager;
    }

    /**
     * TODO 失败处理器
     *
     * @return
     */
    @Bean(name = "failureHandler")
    public AuthenticationFailureHandler simpleUrlAuthenticationFailureHandler() {
        LOGGER.debug("======simpleUrlAuthenticationFailureHandler======");
        return new SimpleUrlAuthenticationFailureHandler("/login?error");
    }

//    /**
//     * 超期处理器
//     *
//     * @param invalidSessionUrl
//     * @return
//     */
//    public InvalidSessionStrategy invalidSessionStrategy(String invalidSessionUrl) {
//        SmartInvalidSessionStrategy smartInvalidSessionStrategy = new SmartInvalidSessionStrategy();
//        smartInvalidSessionStrategy.setInvalidSessionUrl(invalidSessionUrl);
//        return smartInvalidSessionStrategy;
//    }

    /**
     * 表达式控制器
     */
    @Bean(name = "expressionHandler")
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        LOGGER.debug("======webSecurityExpressionHandler======");
        DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
//        webSecurityExpressionHandler.setDefaultRolePrefix("");
        return webSecurityExpressionHandler;
    }

    /**
     * 表达式投票器
     */
    @Bean(name = "expressionVoter")
    public WebExpressionVoter webExpressionVoter() {
        LOGGER.debug("======webExpressionVoter======");
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(webSecurityExpressionHandler());
        return webExpressionVoter;
    }

}
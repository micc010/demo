package com.github.rogerli.config.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager);
//    }
//
//    @Bean(name = "tokenStore")
//    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
//        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
//        return redisTokenStore;
//    }
//
//    @Bean(name = "tokenService")
//    public DefaultTokenServices tokenService(TokenStore tokenStore){
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore);
//        defaultTokenServices.setSupportRefreshToken(true);
//        return defaultTokenServices;
//    }
//
//    @Bean(name = "clinetAuthenticationEntryPoint")
//    public OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint(){
//        OAuth2AuthenticationEntryPoint entryPoint = new OAuth2AuthenticationEntryPoint();
//        return entryPoint;
//    }
//
//    @Bean(name = "accessDeniedHandler")
//    public OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler(){
//        OAuth2AccessDeniedHandler deniedHandler = new OAuth2AccessDeniedHandler();
//        return deniedHandler;
//    }
//
//    @Bean(name = "userApprovalHandler")
//    public DefaultUserApprovalHandler defaultUserApprovalHandler(){
//        DefaultUserApprovalHandler handler = new DefaultUserApprovalHandler();
//        return handler;
//    }

}
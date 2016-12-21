package com.github.rogerli.config.security;

import com.github.rogerli.config.security.WebSecurityConfiguration;
import com.github.rogerli.config.session.RedisSessionConfiguration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    public SecurityInitializer() {
        super(WebSecurityConfiguration.class, RedisSessionConfiguration.class);
    }
}
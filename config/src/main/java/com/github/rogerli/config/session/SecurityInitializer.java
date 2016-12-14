package com.github.rogerli.config.session;

import com.github.rogerli.config.security.WebSecurityConfiguration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    public SecurityInitializer() {
        super(WebSecurityConfiguration.class, RedisSessionConfiguration.class);
    }
}
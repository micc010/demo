package com.github.rogerli.config.jwt.auth.ajax;

import com.github.rogerli.config.jwt.model.UserContext;
import com.github.rogerli.system.login.model.LoginRole;
import com.github.rogerli.system.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 
 * @author vladimir.stankovic
 *
 * Aug 3, 2016
 */
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private LoginService loginService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        Optional<LoginRole> optional = loginService.findRoleByUsername(username);

        LoginRole login = optional.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        
        if (!encoder.matches(password, login.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        if (login.getRoleList() == null) {
            throw new InsufficientAuthenticationException("User has no roles assigned");
        }
        
        List<GrantedAuthority> authorities = login.getRoleList().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(login.getUserName(), login.getOrganId(), authorities);
        
        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}

package com.github.rogerli.config.jwt.endpoint;

import com.github.rogerli.config.jwt.auth.JwtAuthenticationToken;
import com.github.rogerli.config.jwt.model.UserContext;
import com.github.rogerli.system.login.entity.Login;
import com.github.rogerli.system.login.service.LoginService;
import com.github.rogerli.system.user.entity.User;
import com.github.rogerli.system.user.entity.UserMeModel;
import com.github.rogerli.system.user.service.UserService;
import com.github.rogerli.utils.error.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * End-point for retrieving logged-in user details.
 *
 * @author vladimir.stankovic
 *         <p>
 *         Aug 4, 2016
 */
@RestController
public class ProfileEndpoint {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/me", method = RequestMethod.GET)
    @ResponseBody
    public UserMeModel get(JwtAuthenticationToken token) {
        UserContext userContext = (UserContext) token.getPrincipal();
        userContext.setStatus(HttpStatus.OK.value());

        UserMeModel user = userService.findByUsername(userContext.getUsername());
        user.setStatus(userContext.getStatus());
        user.setOrganId(userContext.getOrganId());
        user.setUsername(userContext.getUsername());
        user.setAuthorities(userContext.getAuthorities());

        return user;
    }

}

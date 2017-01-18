package com.github.rogerli.config.jwt.endpoint;

import com.github.rogerli.config.jwt.auth.JwtAuthenticationToken;
import com.github.rogerli.config.jwt.model.UserContext;
import com.github.rogerli.utils.error.ErrorCode;
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

    @RequestMapping(value = "/api/me", method = RequestMethod.GET)
    @ResponseBody
    public UserContext get(JwtAuthenticationToken token) {
        UserContext userContext = (UserContext) token.getPrincipal();
        userContext.setStatus(HttpStatus.OK.value());
        return userContext;
    }
}

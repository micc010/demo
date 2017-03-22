package com.github.rogerli.config.jwt.auth.ajax;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rogerli.config.jwt.model.UserContext;
import com.github.rogerli.config.jwt.token.JwtToken;
import com.github.rogerli.config.jwt.token.JwtTokenFactory;
import com.github.rogerli.system.log.entity.Log;
import com.github.rogerli.system.log.service.LogService;
import com.github.rogerli.system.login.entity.Login;
import com.github.rogerli.system.login.service.LoginService;
import com.github.rogerli.system.purview.entity.Purview;
import com.github.rogerli.system.purview.service.PurviewService;
import com.github.rogerli.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AjaxAwareAuthenticationSuccessHandler
 * 
 * @author vladimir.stankovic
 *
 * Aug 3, 2016
 */
@Component
public class AjaxAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper mapper;
    private final JwtTokenFactory tokenFactory;
    @Autowired
    private LoginService loginService;

    @Autowired
    private LogService logService;

    @Autowired
    public AjaxAwareAuthenticationSuccessHandler(final ObjectMapper mapper, final JwtTokenFactory tokenFactory) {
        this.mapper = mapper;
        this.tokenFactory = tokenFactory;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        UserContext userContext = (UserContext) authentication.getPrincipal();

        // 认证成功同时生成token和refreshToken
        JwtToken accessToken = tokenFactory.createAccessJwtToken(userContext);
        JwtToken refreshToken = tokenFactory.createRefreshToken(userContext);
        
        Map<String, Object> tokenMap = new HashMap<String, Object>();
        tokenMap.put("token", accessToken.getToken());
        tokenMap.put("refreshToken", refreshToken.getToken());
        tokenMap.put("status", HttpStatus.OK.value());

        // 返回权限清单
        Login login = loginService.findByUsername(userContext.getUsername());
        login.setId(login.getId());
        List<Purview> list = loginService.findUserPurview(login);
        tokenMap.put("urls", list);

        // 记录登录日志
        Log log = new Log();
        log.setLoginId(login.getId());
        log.setLoginName(login.getUserName());
        log.setLogIp(request.getRemoteAddr());
        log.setLogTime(new Date());
        log.setLogOperate("login");
        logService.insertSelective(log);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        AjaxResponseHelper.addOrigins(response);
        mapper.writeValue(response.getWriter(), tokenMap);

        clearAuthenticationAttributes(request);
    }

    /**
     * Removes temporary authentication-related data which may have been stored
     * in the session during the authentication process..
     * 
     */
    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}

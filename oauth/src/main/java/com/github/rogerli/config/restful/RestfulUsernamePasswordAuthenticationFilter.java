package com.github.rogerli.config.restful;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rogerli.config.security.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestfulUsernamePasswordAuthenticationFilter extends
        AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_RESTFUL_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_RESTFUL_PASSWORD_KEY = "password";
    public static final String SPRING_SECURITY_RESTFUL_LOGIN_URL = "/api/login";

    private String usernameParameter = SPRING_SECURITY_RESTFUL_USERNAME_KEY;
    private String passwordParameter = SPRING_SECURITY_RESTFUL_PASSWORD_KEY;
    private boolean postOnly = true;

    public RestfulUsernamePasswordAuthenticationFilter() {
        super(new AntPathRequestMatcher(SPRING_SECURITY_RESTFUL_LOGIN_URL, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException,
            IOException, ServletException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String json = getRequestPostStr(request);
        ObjectMapper objectMapper = new ObjectMapper();
        CustomUserDetails userDetails = objectMapper.readValue(json, CustomUserDetails.class);

        String username = userDetails.getUsername();
        String password = userDetails.getPassword();

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);

        // Allow subclasses to set the "details" property  
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

//    protected String obtainPassword(HttpServletRequest request) {
//        return request.getParameter(passwordParameter);
//    }
//
//
//    protected String obtainUsername(HttpServletRequest request) {
//        return request.getParameter(usernameParameter);
//    }

    public byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /**
     * 描述:获取 post 请求内容
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException
     */
    public String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

    protected void setDetails(HttpServletRequest request,
                              UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public String getUsernameParameter() {
        return usernameParameter;
    }

    public void setUsernameParameter(String usernameParameter) {
        this.usernameParameter = usernameParameter;
    }

    public String getPasswordParameter() {
        return passwordParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        this.passwordParameter = passwordParameter;
    }

    public boolean isPostOnly() {
        return postOnly;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public void setLoginUrl(String loginUrl) {
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(loginUrl, "POST"));
    }

}  
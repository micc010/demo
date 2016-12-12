package com.github.rogerli.security.spring;

import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomAuthenticationSuccessEvent extends AuthenticationSuccessEvent {

	private HttpServletRequest httpServletRequest;
	private HttpServletResponse httpServletResponse;
	
	public HttpServletResponse getHttpServletResponse() {
		return httpServletResponse;
	}

	public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}

	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	public CustomAuthenticationSuccessEvent(Authentication authentication, HttpServletRequest request, HttpServletResponse respones) {
		super(authentication);
		httpServletRequest = request;
		httpServletResponse = respones;
	}
}

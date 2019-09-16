package com.compasso.backend.app.pattern.rest;

import com.compasso.backend.app.CompassoContext;
import com.compasso.backend.app.security.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
public abstract class AbstractRestResource {

	@Autowired
	private HttpServletRequest request;
	
	protected CompassoContext getContext() {
		String header = request.getHeader("Authorization");
        CompassoContext context = TokenAuthenticationService.getContextByToken(header);
        return context;
	}
	
}

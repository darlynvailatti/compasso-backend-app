package com.compasso.backend.app.rest.resource.impl;

import com.compasso.backend.app.rest.resource.ILogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginResourceImpl implements ILogin {

	private static final Logger log = LoggerFactory.getLogger(LoginResourceImpl.class);
	
	
}

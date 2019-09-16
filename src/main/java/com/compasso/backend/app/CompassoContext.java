package com.compasso.backend.app;

public class CompassoContext {
	
	private String userLogin;
	
	public CompassoContext(String userLogin) {
		super();
		this.userLogin = userLogin;
	}

	public CompassoContext() {
		super();
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUsuarioLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	
	
}

package com.compasso.backend.app.util;

import java.time.LocalDate;

public class Date {

	private LocalDate content;
	
	public Date(LocalDate content) {
		super();
		this.content = content;
	}

	public LocalDate content() {
		return this.content;
	}
	
	public static Date today() {
		return new Date(LocalDate.now()); 
	}
	
}

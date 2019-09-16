package com.compasso.backend.app.exception;

public class BusinessLogicException extends Exception{

	private static final long serialVersionUID = 1L;

	public BusinessLogicException(Exception e){
        super(e);
    }

	public BusinessLogicException() {
		super();
	}

	public BusinessLogicException(String message) {
		super(message);
	}

}

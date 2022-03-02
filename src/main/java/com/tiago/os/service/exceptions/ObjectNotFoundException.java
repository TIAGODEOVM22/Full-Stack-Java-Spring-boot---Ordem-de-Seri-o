package com.tiago.os.service.exceptions;

public class ObjectNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String message, Throwable cause) { //recebe uma string e a causa do erro
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ObjectNotFoundException(String message) { //recebe uma string
		super(message);
		// TODO Auto-generated constructor stub
	}
	

}

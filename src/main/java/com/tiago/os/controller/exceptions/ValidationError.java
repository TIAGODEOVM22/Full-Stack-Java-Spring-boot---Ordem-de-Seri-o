package com.tiago.os.controller.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> erros = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(long timestamp, Integer status, String error) {
		super(timestamp, status, error);
	}

	public List<FieldMessage> getErros() {
		return erros;
	}

	//SET ERROR FOI ALTERADO PARA  *ADDERROR*
	public void addError(String fieldName , String message) {
		this.erros.add(new FieldMessage(fieldName, message));
	}
	
	
}

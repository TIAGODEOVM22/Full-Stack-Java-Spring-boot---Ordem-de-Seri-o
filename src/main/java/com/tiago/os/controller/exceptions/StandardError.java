package com.tiago.os.controller.exceptions;

import java.io.Serializable;

/*essa classe servce de apoio a classe ControllerExceptionHandler*/
public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long timestamp; /*momento em que ocorreu o erro*/
	private Integer status; /*valor do erro*/
	private String error;
	
	public StandardError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StandardError(long timestamp, Integer status, String error) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
}

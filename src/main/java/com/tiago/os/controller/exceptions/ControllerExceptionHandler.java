package com.tiago.os.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tiago.os.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	/*ObjectNotFoundException essa classe é do subpacote service exceptions */
	/*@ExceptionHandler anotação para lidar co excessões*/
	@ExceptionHandler(ObjectNotFoundException.class)
				/*objectNotFoundException nome do metodo e recebe um objectNotFoundException chamado de e*/	
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e){
		/*StandarError é uma classe do subpacote controller.exceptions*/
		
		/*classe StandardError instanciada de acordo com seu construtor.  pacote controller.exception*/
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);/*no corpo da resposta nós passamos o StandardError "error"*/
	}

}

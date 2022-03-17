package com.tiago.os.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tiago.os.service.exceptions.DataIntegratyViolationException;
import com.tiago.os.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	/*ObjectNotFoundException essa classe é do subpacote service exceptions ela que sera manipulada*/
	/*@ExceptionHandler anotação para lidar co excessões*/
	
	@ExceptionHandler(ObjectNotFoundException.class)
	
	/*objectNotFoundException nome do metodo e recebe um objectNotFoundException chamado de e*/	
	
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e){
		
	/*StandarError é uma classe do subpacote controller.exceptions*/	
	/*classe StandardError instanciada de acordo com seu construtor.  pacote controller.exception*/
		
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
		
	//1*system.currentTimeMillis() OCORREU AGORA 2*HttpStattus.Not_Found.value() valor do status 3*pegar a mns do error
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);/*no corpo da resposta nós passamos o StandardError "error"*/
		
	}
	
	/*MANIPULADOR DA CLASSE DataIntegratyViolationException.class*/
	@ExceptionHandler(DataIntegratyViolationException.class)
	public ResponseEntity<StandardError> objectNotFoundException(DataIntegratyViolationException e){
			StandardError error = new StandardError(System.currentTimeMillis(),
					HttpStatus.BAD_REQUEST.value(), e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
			
		}
	
	/*MANPULADOR DA CLASSE ValidationError*/
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> objectNotFoundException(MethodArgumentNotValidException e){
			ValidationError error = new ValidationError(System.currentTimeMillis(),
					HttpStatus.BAD_REQUEST.value(),"Erro na validação dos campos!");

			
			for(FieldError x : e.getBindingResult().getFieldErrors()) {
				error.addError(x.getField(), x.getDefaultMessage());
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
	/*A classe validationError erda da StandartError  por isso ela tem os atributos
	 * timestamp, status e error...
	 * Foi criado um FOR para listar os erros de cada campo se o campo 
	 * não tiver nem uma informação inserida...
	 * Se for Pegar mais campos para tratamento, temos que incluir la na classe FIELDMESSAGE*/
}


package com.tiago.os.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiago.os.dtos.OsDTO;
import com.tiago.os.service.OsService;

@RestController
@RequestMapping(value = "/os") /*endPoint Inicial para acessar os recursos da classe OS*/
public class OsController {
	
	@Autowired
	private OsService osService;

	@GetMapping(value="/{id}")
	public ResponseEntity<OsDTO>findById(@PathVariable Integer id){
		OsDTO objDTO = new OsDTO(osService.findById(id));
		return ResponseEntity.ok().body(objDTO);
	}
}

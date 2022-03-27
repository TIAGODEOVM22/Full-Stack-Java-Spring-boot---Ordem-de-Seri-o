package com.tiago.os.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tiago.os.dtos.OsDTO;
import com.tiago.os.model.Os;
import com.tiago.os.service.OsService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/os") /*endPoint Inicial para acessar os recursos da classe OS*/
public class OsController {
	
	@Autowired
	private OsService osService;

	/*_____________RETORNA ORDEM DE SERVIÇO POR ID________________*/
	@GetMapping(value="/{id}")
	public ResponseEntity<OsDTO>findById(@PathVariable Integer id){
		OsDTO objDTO = new OsDTO(osService.findById(id));
		return ResponseEntity.ok().body(objDTO);
	}
	
	/*___________RETORNA LISTA DE ORDEM SE SERVIÇO______________*/
	@GetMapping
	public ResponseEntity<List<OsDTO>>findAll(){
		List<Os>list = osService.findAll();/*List recebe a lista do Service*/
		List<OsDTO>listDTO = new ArrayList<>();/*Cria uma lista vazia*/
		
		for(Os obj : list) {/*para cada objeto da lista model*/
			listDTO.add(new OsDTO(obj));/*eu adiciono na lista DTO como ObjDTO*/
		}
		return ResponseEntity.ok().body(listDTO);/*retorno a listaDTO já transformada*/
	}
	
	/*______________CRIA UMA ORDEM DE SERVIÇO_______________*/
	@PostMapping
	public ResponseEntity<OsDTO>create(@Valid @RequestBody OsDTO obj){
		obj = new OsDTO(osService.create(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
		/*Criamos a URI de acesso a OS que esta sendo criada*/
		
	}
	@PutMapping
	public ResponseEntity<OsDTO> update(@Valid @RequestBody OsDTO obj){
		obj = new OsDTO(osService.update(obj));
		return ResponseEntity.ok().body(obj);
		
		/*criamos esse método de maneira diferente da classe tecnico afim
		 * de aprendermos diversas maneiras de atualizar um OBJETO*/
	}
	
}

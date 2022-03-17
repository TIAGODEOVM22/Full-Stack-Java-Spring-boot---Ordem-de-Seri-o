package com.tiago.os.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tiago.os.dtos.TecnicoDTO;
import com.tiago.os.model.Tecnico;
import com.tiago.os.service.TecnicoService;

@RestController // classe controladora gerenciando as requisições http
@RequestMapping(value="/tecnicos") // endpoint inicial, ou seja nome da url
public class TecnicoController {

	@Autowired
	private TecnicoService tecnicoService;

	/*
	 * endpoint final variavel id quando informo entre {} significa variavel de path
	 * então no parametro tenho que passar pathVariable o tipo do ID e o id
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		TecnicoDTO objDTO = new TecnicoDTO(
				tecnicoService.findById(id));/*
												 * para criar esse metodo a classe TecnicoService tem que estar com o
												 * metodo findById implementado...
												 */
		return ResponseEntity.ok().body(objDTO);

	}

	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		List<TecnicoDTO> listDTO = tecnicoService.findAll()
				.stream().map(obj -> new TecnicoDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	/*____________________TAMBÉM PODE SER FEITO DESSE JEITO O BUSCAR TODOS___________________________*/
	/*@GetMapping
	  public ResponseEntity<List<TecnicoDTO>>findAll(){ 
		  List<Tecnico> list = tecnicoService.findAll(); List<TecnicoDTO> listDTO = new ArrayList<>();
	      for(Tecnico obj : list) { listDTO.add(new TecnicoDTO(obj)); } 
	      return ResponseEntity.ok().body(listDTO); 
	      }*/
	
	/*_________________CREATE DE TECNICO PASSANDO A URI DO NOVO OBJ CRIADO NO BD_________________*/
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){
		Tecnico tecModel = tecnicoService.create(objDTO);/*Tecnico MODEL recebe um tecnico DTO*/
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecModel.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
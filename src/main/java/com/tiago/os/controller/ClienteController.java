package com.tiago.os.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tiago.os.dtos.ClienteDTO;
import com.tiago.os.model.Cliente;
import com.tiago.os.service.ClienteService;

@CrossOrigin("*")
@RestController // classe controladora gerenciando as requisições http
@RequestMapping(value="/clientes") // endpoint inicial, ou seja nome da url
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	/*
	 * endpoint final variavel id quando informo entre {} significa variavel de path
	 * então no parametro tenho que passar pathVariable o tipo do ID e o id
	 */
	
	
	/*________________RETORNA UM TECNICO PELO ID____________________*/
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		ClienteDTO objDTO = new ClienteDTO(
				clienteService.findById(id));
		return ResponseEntity.ok().body(objDTO);
		/*
		 * para criar esse metodo a classe ClienteService tem que estar com o
		 * metodo findById implementado...
		 */
	}
	
	/*________________LISTA TODOS OS TECNICOS__________________*/
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<ClienteDTO> listDTO = clienteService.findAll()
				.stream().map(obj -> new ClienteDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	/*______________TAMBÉM PODE SER FEITO DESSE JEITO O BUSCAR TODOS_______________*/
	/*@GetMapping
	  public ResponseEntity<List<ClienteDTO>>findAll(){ 
		  List<Cliente> list = clienteService.findAll();
		  List<ClienteDTO> listDTO = new ArrayList<>();
		  
	      for(Cliente obj : list) {
	    	  listDTO.add(new ClienteDTO(obj)); } 
	      return ResponseEntity.ok().body(listDTO); 
	      }*/
	
	/*_________________CREATE DE TECNICO PASSANDO A URI DO NOVO OBJ CRIADO NO BD_________________*/
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO){
		Cliente tecModel = clienteService.create(objDTO);/*Cliente MODEL recebe um tecnico DTO*/
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecModel.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/*________________ATUALIZA TECNICO______________*/
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDTO){
		ClienteDTO newObj = new ClienteDTO(clienteService.update(id, objDTO));
		return ResponseEntity.ok().body(newObj);
		
		/*recebe o tecnicoDTO da chamada service Update ja atualizado e passa o newObj */
	}
	
	
	/*________________________DELETAR TECNICO_____________________*/
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String>delete(@PathVariable Integer id){
		clienteService.delete(id);
		return ResponseEntity.ok().body("Cliente Deletado com sucesso");
		
		//ResponseEntity era Void passei pra String
		//return ResponseEntity.noContent().build();
		/*recebe o id passado pela service
		 * noContent() sem conteudo algum*/
	}
}


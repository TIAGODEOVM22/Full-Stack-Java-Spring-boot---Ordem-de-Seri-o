package com.tiago.os.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiago.os.dtos.OsDTO;
import com.tiago.os.model.Cliente;
import com.tiago.os.model.Os;
import com.tiago.os.model.Tecnico;
import com.tiago.os.model.enuns.Prioridade;
import com.tiago.os.model.enuns.Status;
import com.tiago.os.repository.OsRepository;
import com.tiago.os.service.exceptions.ObjectNotFoundException;

@Service 
public class OsService {
	
	@Autowired
	private OsRepository osRepository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	/*__________________RETORNA UMA ORDEM DE SERVIÇO POR ID__________________*/
	public Os findById(Integer id) {
		Optional<Os> obj = osRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Ordem de Serviço não encontrada, id: "
		+ id + ", tipo:" + Os.class.getName()));
	
	}
	
	/*_______________RETORNA TODAS AS ORDENS DE SERVIÇO_____________*/
	public List <Os> findAll(){
		return osRepository.findAll();
	}

	/*______________CRIA UMA NOVA ORDEM DE SERVIÇO_________________*/
	public Os create(@Valid OsDTO obj) {
		
		return fromDTO(obj);

	}
	
	/*_________TRANSFORMA DE OsDTO PARA OsMODEL E SETA UM TECNICO E UM CLIENTE NA OS __________ */
	private Os fromDTO(OsDTO obj) {
		Os newObj = new Os();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		return osRepository.save(newObj);
	
		/*DTO é um objeto de transferencia de dados, então não podemos salvar ele
		 * temos que transformar para MODEL, por isso criamos esse metodo privado
		 * apenas essa classe tem acesso a ele.*/
		
	}

	
	
}

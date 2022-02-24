package com.tiago.os.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiago.os.model.Tecnico;
import com.tiago.os.repository.TecnicoRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	/*criei esse metodo para retornar o id
	 * porém atraves desse pacote eu não tenho acesso ao BD
	 * então eu vou fazer o import da interface TECNICOREPOSITORY*/
	
	public Tecnico findById(Integer id) {
		/*pode encontrar um tecnico no BD ou não
		 * por isso uso o OPTIONAL*/
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElse(null);//pode ou não encontrar orElse, se não retorna NULL, ainda vamos tratar esse retorno...
	}
}

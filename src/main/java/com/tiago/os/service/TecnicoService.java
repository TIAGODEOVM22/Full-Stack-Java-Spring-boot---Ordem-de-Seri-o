package com.tiago.os.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiago.os.model.Tecnico;
import com.tiago.os.repository.TecnicoRepository;
import com.tiago.os.service.exceptions.ObjectNotFoundException;

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
		return obj.orElseThrow(() -> new ObjectNotFoundException(/*criação de uma função anonima. -> */
				"Objeto não encontrado! id: " + id + ", tipo: " + Tecnico.class.getName()));
		/*Apenas a criação dessa exceção não basta
		 * vamos criar uma classe controller exceptionHandler em um sub pacote no Controller*/
	}
}

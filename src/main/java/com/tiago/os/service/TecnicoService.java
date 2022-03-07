package com.tiago.os.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiago.os.dtos.TecnicoDTO;
import com.tiago.os.model.Tecnico;
import com.tiago.os.repository.TecnicoRepository;
import com.tiago.os.service.exceptions.DataIntegratyViolationException;
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
	
	/*______________________RETORNA TODOS OS TECNICOS___________________________*/
	
	public List<Tecnico> findAll(){
		return tecnicoRepository.findAll();
	}
	
	
	/*___________________VERIFICA SE O CPF DO OBJ JA EXISTE NA BASE DE DADO_________________*/
		private Tecnico findByCPF(TecnicoDTO objDTO) {
			Tecnico obj = tecnicoRepository.findByCPF(objDTO.getCpf());
			if(obj != null) {
					return obj;
			}
			return null;
		}
	
	/*______________ANTES DE CRIAR UM TECNICO, FAZ A VERIFICAÇÃO SE JA EXISTE UM CPF IGUAL NA BD.________________*/
	public Tecnico create(TecnicoDTO objDTO) {
		if(findByCPF(objDTO) != null){
			throw new DataIntegratyViolationException("CPF já cadastrado na Base de Dados!");
		}
		return tecnicoRepository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}
	
	/*____________________MANEIRA 1º CRIA TECNICO E PASSA OS ATRIBUTOS PARA O OBJTO DTO________________
	public Tecnico create(TecnicoDTO objDTO) {
		return tecnicoRepository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}*/
	
	/*___________________MANEIRA 2º CRIA TECNICO E PASSA OS ATRIBUTOS PARA O OBJTO DTO____________________________
	public Tecnico create(TecnicoDTO objDTO) {
		Tecnico obj = new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		return tecnicoRepository.save(obj);
	}*/
}
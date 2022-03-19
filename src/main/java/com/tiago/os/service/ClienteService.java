package com.tiago.os.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiago.os.dtos.ClienteDTO;
import com.tiago.os.model.Pessoa;
import com.tiago.os.model.Cliente;
import com.tiago.os.repository.PessoaRepository;
import com.tiago.os.repository.ClienteRepository;
import com.tiago.os.service.exceptions.DataIntegratyViolationException;
import com.tiago.os.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	/*
	 *_____________RETORNA UM CLIENTE PELO ID OU EXIBE MENSAGEM QUE NÃO ENCONTROU________________
	 */
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(/*criação de uma função anonima. -> */
				"Objeto não encontrado! id: " + id + ", tipo: " + Cliente.class.getName()));
		
		/*pode encontrar um tecnico no BD ou não
		 * por isso uso o OPTIONAL
		 * Apenas a criação dessa exceção não basta
		 * vamos criar uma classe controller exceptionHandler em um sub pacote no Controller*/
	}
	
	/*______________________RETORNA TODOS OS CLIENTES___________________________*/
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	/*_______________________________ATUALIZA CLIENTE_____________________________________________*/
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findById(id);/*-----> old é termo para objeto velho <-----*/
		if(findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na Base de Dados!");
		}
			oldObj.setNome(objDTO.getNome());
			oldObj.setCpf(objDTO.getCpf());
			oldObj.setTelefone(objDTO.getTelefone());
			return clienteRepository.save(oldObj);
			
			/*se o CPF do ObjDTO for diferente de vazio e diferente do id passado no parametro desse metodo
			 * então o CPF já existe e é um cpf diferente ja cadastrado para esse usuario,
			 * só vai atualizar a informação se o cpf existir e for o mesmo...*/
	}
	
	/*____________DELETAR CLIENTE________________*/
	public void delete(Integer id) {
		
		Cliente obj = findById(id);
		
		if(obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Pessoa possui Ordem de Serviço e não pode ser deletado!");
		}
		clienteRepository.deleteById(id);
		
		/*findById(id) faz a verifiação se o ID informado existe antes de deletar
		 * passa o id informado ao obj, se o obj tiver em sua lista de OS alguma OS esse tecnico não pode ser deletado*/
	}
	
	/*___________________BUSCA POR CPF_________________*/
		private Pessoa findByCPF(ClienteDTO objDTO) {
			Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
			if(obj != null) {
					return obj;
			}
			return null;
		}
	
	/*______________ANTES DE CRIAR UM CLIENTE, FAZ A VERIFICAÇÃO SE JA EXISTE UM CPF IGUAL NA BD.________________*/
	public Cliente create(ClienteDTO objDTO) {
		if(findByCPF(objDTO) != null){
			throw new DataIntegratyViolationException("CPF já cadastrado na Base de Dados!");
		}
		return clienteRepository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	
	
	/*____________________MANEIRA 1º CRIA CLIENTE E PASSA OS ATRIBUTOS PARA O OBJTO DTO________________
	public Cliente create(ClienteDTO objDTO) {
		return clienteRepository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}*/
	
	/*___________________MANEIRA 2º CRIA CLIENTE E PASSA OS ATRIBUTOS PARA O OBJTO DTO____________________________
	public Cliente create(ClienteDTO objDTO) {
		Cliente obj = new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		return clienteRepository.save(obj);
	}*/
}
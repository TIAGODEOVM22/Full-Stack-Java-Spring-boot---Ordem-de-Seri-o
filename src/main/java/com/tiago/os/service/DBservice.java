package com.tiago.os.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiago.os.model.Cliente;
import com.tiago.os.model.Os;
import com.tiago.os.model.Tecnico;
import com.tiago.os.model.enuns.Prioridade;
import com.tiago.os.model.enuns.Status;
import com.tiago.os.repository.ClienteRepository;
import com.tiago.os.repository.OsRepository;
import com.tiago.os.repository.TecnicoRepository;

@Service
public class DBservice {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OsRepository osRepository;
	
	
	public void instaciaDB() {
		
		Tecnico t1 = new Tecnico(null, "Tiago Oliveira", "212.745.310-71", "(67)9999-1234");
		Tecnico t2 = new Tecnico(null, "Tiago Villalva", "662.924.010-30", "(67)9876-4321");
		Cliente c1 = new Cliente(null, "Betina Campos", "875.115.600-83", "(67)9999-3210");
		
		Os os1 = new Os(null, Prioridade.ALTA, "Teste Create OS",Status.ANDAMENTO, t1,c1) ;
		/*Atraves dos repositorys iremos conseguir salvar essas instancias na BD*/
		
		t1.getList().add(os1);//adiciona o tecnico a OS1
		c1.getList().add(os1);//adiciona o cliente a OS1
		
		/*salva uma lista de tecnico, cliente ou OS
		 * não precisando dar ADD toda vez que for salvar
		 * se eu tivesse mais tecnicos era só ir colocando, t2,t3...*/
		tecnicoRepository.saveAll(Arrays.asList(t1,t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}

}

package com.tiago.os.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiago.os.model.Os;
import com.tiago.os.repository.OsRepository;
import com.tiago.os.service.exceptions.ObjectNotFoundException;

@Service 
public class OsService {
	
	@Autowired
	private OsRepository osRepository;
	
	public Os findById(Integer id) {
		Optional<Os> obj = osRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Ordem de Serviço não encontrada, id: "
		+ id + ", tipo:" + Os.class.getName()));
	
	}
}

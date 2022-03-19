package com.tiago.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tiago.os.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	
	@Query("SELECT obj FROM Pessoa obj WHERE obj.cpf =:cpf")/*VERIFICA NO BD*/
	Pessoa findByCPF(@Param("cpf") String cpf);

}

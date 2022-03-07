package com.tiago.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tiago.os.model.Tecnico;

/*A interface extends o JpaRepository que irá fazer as persitencias no BD
 * então temos que passar como parametro a classe model e o tipo do ID*/
@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{

	
	@Query("SELECT obj FROM Tecnico obj WHERE obj.cpf =:cpf")/*VERIFICA NO BD*/
	Tecnico findByCPF(@Param("cpf") String cpf);
	
}
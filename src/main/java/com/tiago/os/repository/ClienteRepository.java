package com.tiago.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiago.os.model.Cliente;
/*A interface extends o JpaRepository que irá fazer as persitencias no BD
 * então temos que passar como parametro a classe model e o tipo do ID*/
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}

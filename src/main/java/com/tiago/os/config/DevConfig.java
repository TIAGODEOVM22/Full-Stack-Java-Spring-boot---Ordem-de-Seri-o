package com.tiago.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.tiago.os.service.DBservice;

@Configuration
@Profile("dev")/*Através desta anotação o aplication.properties
identifica que temos um perfil e vamos chama-lo de dev*/
public class DevConfig {

	@Autowired
	private DBservice dBservice;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")/*pega o valor estipulado no application.properties
	deste perfil*/
	private String ddl;//Data Definition Language
	
	@Bean
	public boolean instanciaDB() {
		if(ddl.equals("create")) {
			this.dBservice.instaciaDB();
			/*se for VDD ou seja se a variavel DDL que recebe o "spring.jpa.hibernate.ddl-auto"
			 * estiver configurado com CREATE, ele cria a instanciaDB*/
		}
		return false;
	}
}



package com.tiago.os.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tecnico extends Pessoa{
	/*não contem nem um atributo adicional, todos são da superclasse*/
	
	/*Referente ao Diagrama
	 * zero ou muitas OS*/
	@JsonIgnore //ignora essa LISTA quando eu der um GET no tecnico by id, retornando apenas o TECNICO....
	@OneToMany(mappedBy = "tecnico")
	private List<Os> list = new ArrayList<>();
	
	
	public Tecnico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tecnico(Integer id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
		// TODO Auto-generated constructor stub
	}

	public List<Os> getList() {
		return list;
	}

	public void setList(List<Os> list) {
		this.list = list;
	}

	
}

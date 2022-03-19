package com.tiago.os.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa {
	private static final long serialVersionUID = 1L;
	/*Referente ao Diagrama
	 * zero ou muitas Os*/
	@OneToMany(mappedBy = "cliente")
	private List<Os> list = new ArrayList<>();
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(Integer id, String nome, String cpf, String telefone) {
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
package com.tiago.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tiago.os.model.Os;

public class OsDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyy HH:mm")
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyy HH:mm")
	private LocalDateTime dataFechamento;
	
	private Integer prioridade; 
	private String observacoes;
	private Integer status; 
	private Integer tecnico; /*Mudei do tipo Tecnico para tipo Integer*/
	private Integer cliente; /*Mudei do tipo Cliente para tipo Integer*/
	
	public OsDTO() {
		super();
		
	}
	
	public OsDTO(Os obj) {/*Mudei os parametros, e passei a receber apenas um Objeto de Os Model*/
		super();
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		this.prioridade = obj.getPrioridade().getCod();/*tem que pegar o cod porque é Integer*/
		this.observacoes = obj.getObservacoes();
		this.status = obj.getStatus().getCod();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
	}
	
	/*GETTERS AND SETTERS
	 * só não gerei o get e set do serialVersionUID*/
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	
	
	
}
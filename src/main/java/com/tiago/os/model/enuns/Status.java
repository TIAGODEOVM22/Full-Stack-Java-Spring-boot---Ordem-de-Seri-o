package com.tiago.os.model.enuns;

public enum Status {
	ABERTO(0, "ABERTO"),
	ANDAMENTO(1, "ANDAMENTO"), 
	FECHADO(2, "FECHADO");
	
	private Integer cod;
	private String descricao;
	
	private Status(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	//Retorna um Status
	public static Status toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		//para cada Status x dentro da nossa classe Status
		for(Status x : Status.values()) {
			if(cod.equals(x.getCod())) {
				return x;//existe um status
			}
		}
		throw new IllegalArgumentException("Status inválido!" + cod);
	}
}

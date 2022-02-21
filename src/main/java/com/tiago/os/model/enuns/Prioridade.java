package com.tiago.os.model.enuns;

public enum Prioridade {

	BAIXA(0, "BAIXA"),
	MEDIA(1, "MEDIA"), 
	ALTA(2, "ALTA");
	
	private Integer cod;
	private String descricao;
	
	private Prioridade(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	//Retorna uma Prioridade
	public static Prioridade toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		//para cada Prioridade x dentro da nossa classe Prioridade
		for(Prioridade x : Prioridade.values()) {
			if(cod.equals(x.getCod())) {
				return x;//existe uma prioridade
			}
		}
		throw new IllegalArgumentException("Prioridade inválida!" + cod);
	}
}

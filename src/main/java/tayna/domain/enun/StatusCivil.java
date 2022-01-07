package tayna.domain.enun;

import tayna.services.exceptions.ObjectNotFoundException;

public enum StatusCivil {

	SOLTEIRO(1, "Solteiro"),
	NAMORANDO(2, "Namorando"),
	CASADO(3, "Casado"),
	VIUVO(1, "Viuvo");
	
	private int cod;	
	private String descricao;
	
	private StatusCivil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public boolean isNotValid(StatusCivil tipo) {
		 if (tipo == null) {
		      throw new ObjectNotFoundException("Tipo de status civil invalido");
		    }
		 return false;
	}
}

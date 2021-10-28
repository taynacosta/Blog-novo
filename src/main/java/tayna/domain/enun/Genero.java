package tayna.domain.enun;

import tayna.services.exceptions.ObjectNotFoundException;

public enum Genero {

	FEMININO(1, "Feminino"),
	MASCULINO(2, "Masculino"),
	GENERO_NEUTRO(3, "Genero neutro"),
	OUTRO(4, "Outro");
	
	private int cod;
	private String descricao;
	
	private Genero(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Genero toEnum(Integer cod) {
		if (cod == null) {
			return Genero.OUTRO;
		}
		for (Genero x : Genero.values()) {
			return x;
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

	public boolean isNotValid(Genero tipo) {
		 if (tipo == null) {
		      throw new ObjectNotFoundException("Tipo de genero não é invalido");
		    }
		 return false;
	}
}

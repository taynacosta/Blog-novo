package tayna.domain.enun;

import tayna.services.exceptions.ObjectNotFoundException;

public enum TipoDePost {

	TEXTO(1, "Publicação Texto"),
	VIDEO(2, "Publicação Vídeo"), 
	FOTO(3, "Publicação Foto");
	
	private int cod;
	
	private String descricao;
	
	private TipoDePost(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoDePost toEnum(Integer cod) {
		if (cod == null) {
			return TipoDePost.FOTO;
		}
		for (TipoDePost x : TipoDePost.values()) {
			return x;
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

	public boolean isNotValid(TipoDePost tipo) {
		 if (tipo == null) {
		      throw new ObjectNotFoundException("Tipo de post invalido");
		    }
		 return false;
	}

}

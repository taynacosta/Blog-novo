package tayna.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import tayna.domain.Comentarios;
import tayna.domain.enun.TipoDePost;

public class PostDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String legenda;
	
	private List <Comentarios> comentarios = new ArrayList<>();
	
	@NotNull(message="O campo tipo é de preenchimento obrigatório")
	private TipoDePost tipo;
	
	public PostDTO() {}

	public PostDTO(Integer id, String legenda, List<Comentarios> comentarios, TipoDePost tipo) {
		this.id = id;
		this.legenda = legenda;
		this.comentarios = comentarios;
		this.setTipo(tipo);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLegenda() {
		return legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}

	public List<Comentarios> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentarios> comentarios) {
		this.comentarios = comentarios;
	}

	public TipoDePost getTipo() {
		return tipo;
	}

	public void setTipo(TipoDePost tipo) {
		this.tipo = tipo;
	}
	
}

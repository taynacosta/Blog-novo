package tayna.dto;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import tayna.domain.Comentarios;

public class ComentarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String conteudo;
	
	private Integer postId;
	
	Comentarios comentario = new Comentarios();
	
	public ComentarioDTO(Integer id, String conteudo, Integer postId) {
		this.id = id;
		this.conteudo = conteudo;
		this.postId = postId;
	}
	
	public ComentarioDTO(Comentarios comentario) {
		this.id = comentario.getId();
		this.conteudo = comentario.getConteudo();
		this.postId = comentario.getPostIdDTO();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

}

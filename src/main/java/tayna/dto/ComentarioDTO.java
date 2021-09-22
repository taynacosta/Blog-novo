package tayna.dto;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import tayna.domain.Comentario;
import tayna.domain.Post;

public class ComentarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String conteudo;
	
	private Integer postId;
	
	Comentario comentario = new Comentario();
	
	public ComentarioDTO(Integer id, String conteudo, Integer postId) {
		this.id = id;
		this.conteudo = conteudo;
		this.postId = postId;
	}
	
	public ComentarioDTO() {}
	
	public ComentarioDTO (Comentario entity) {
		this.id = entity.getId();
		this.conteudo = entity.getConteudo();
		this.postId = entity.getPost().getId();
	}
	
	public static ComentarioDTO from(Comentario comentario) {
		return new ComentarioDTO(comentario);
	}
	
	public Comentario to(Post post) {
		return new Comentario(this.id, this.conteudo, post);
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

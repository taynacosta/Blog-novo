package tayna.dto;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import tayna.domain.Comentario;
import tayna.domain.Post;
import tayna.domain.Usuario;

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
	
	/*
	public ComentarioDTO(Comentario comentario) {
		this.id = comentario.getId();
		this.conteudo = comentario.getConteudo();
		this.postId = comentario.getPost().getId();
		this.post = comentario.getPost();
	}*/
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
	/*public Post to(Usuario usuario) {
		return new Post(this.id,this.legenda,this.tipo, usuario);
	}*/
	
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

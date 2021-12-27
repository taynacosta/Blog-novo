package tayna.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import tayna.domain.Comentario;
import tayna.domain.Post;
import tayna.domain.Usuario;
import tayna.repositories.UsuarioRepository;

public class ComentarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	UsuarioRepository service;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String conteudo;
	
	private Integer postId;
	
	private String nomeUsuario;
	
	private Integer usuarioId;
	
	private Usuario usuario;
	
	Comentario comentario = new Comentario();
	
	public ComentarioDTO(Integer id, String conteudo, Integer postId, Usuario usuario, String nomeUsuario) {
		this.id = id;
		this.conteudo = conteudo;
		this.postId = postId;
		this.usuario = usuario;
		this.nomeUsuario = usuario.getNomeUsuario();
	}
	
	public ComentarioDTO() {}
	
	public ComentarioDTO (Comentario entity) {
		this.id = entity.getId();
		this.conteudo = entity.getConteudo();
		this.postId = entity.getPost().getId();
		this.usuario = entity.getUsuario();
	}
	
	public static ComentarioDTO from(Comentario comentario) {
		return new ComentarioDTO(comentario);
	}
	
	public Comentario to(Post post) {
		return new Comentario(this.id, this.conteudo, post, this.usuario);
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

}

package tayna.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tayna.domain.Comentario;
import tayna.domain.Post;
import tayna.domain.Usuario;
import tayna.domain.enun.TipoDePost;

public class PostDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String legenda;
	
	private List <Comentario> comentarios = new ArrayList<>();
	
	@NotNull(message="O campo tipo é de preenchimento obrigatório") 
	private TipoDePost tipo;
	
	@JsonIgnore
	private Usuario usuario;
	
	private Integer usuarioId;
	
	private String nomeUsuario;
	
	public PostDTO() {}

	public PostDTO(Integer id, String legenda,/* List<Comentario> comentarios,*/ TipoDePost tipo, Usuario usuario, Integer usuarioId) {
		this.id = id;
		this.legenda = legenda;
		//this.comentarios = comentarios;
		this.setTipo(tipo);
		this.usuario = usuario;
		this.usuarioId = usuarioId;
	}
	
	public PostDTO(Post entity) {
		this.id = entity.getId();
		this.legenda = entity.getLegenda();
		this.comentarios = entity.getComentarios();
		this.tipo = entity.getTipo();
		this.usuario = entity.getUsuario();
		this.usuarioId = entity.getUsuario().getId();
	}

	public static PostDTO from(Post post) {
		return new PostDTO(post);
	}
	
	public Post to(Usuario usuario) {
		return new Post(this.id,this.legenda,this.tipo, usuario);
	
	}
	
	public static List<PostDTO> fromList(List<Post> posts) {
		return posts.stream().map(PostDTO::from).collect(Collectors.toList());
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

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public TipoDePost getTipo() {
		return tipo;
	}

	public void setTipo(TipoDePost tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNomeUsuario() {
		return usuario.getNomeUsuario();
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	
}

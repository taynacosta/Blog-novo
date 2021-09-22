package tayna.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import tayna.domain.Comentario;
import tayna.domain.Post;
import tayna.domain.Usuario;
import tayna.domain.enun.TipoDePost;

public class PostDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotBlank
	private String legenda;
	
	private List <Comentario> comentarios = new ArrayList<>();
	
	@NotNull(message="O campo tipo é de preenchimento obrigatório") 
	private TipoDePost tipo;
	
	@NotNull
	private Integer usuarioId;
	
	public PostDTO() {}

	public PostDTO(Integer id, String legenda, List<Comentario> comentarios, TipoDePost tipo, Integer usuarioId) {
		this.id = id;
		this.legenda = legenda;
		this.comentarios = comentarios;
		this.setTipo(tipo);
		this.usuarioId = usuarioId;
	}
	
	public PostDTO(Post entity) {
		this.id = entity.getId();
		this.legenda = entity.getLegenda();
		this.comentarios = entity.getComentarios();
		this.tipo = entity.getTipo();
	}

	public static PostDTO from(Post post) {
		return new PostDTO(post);
	}
	
	public Post to(Usuario usuario) {
		return new Post(this.id,this.legenda,this.tipo, usuario);
	
	}
	
	public static List<PostDTO> fromList(List<Post> posts) {
		return posts.stream().map(PostDTO::from).collect(Collectors.toList());
		//return posts.stream().map(post->from(post)).collect(Collectors.toList());
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
	
	public Integer getUsuarioId() {
		return usuarioId;
	}
	
}

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
import tayna.domain.Likes;
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
	
	private List<Likes> likes = new ArrayList<>();
	
	private Integer qtdLikes;
	
	public PostDTO() {}

	public PostDTO(Integer id, String legenda,TipoDePost tipo, String nomeUsuario, Integer usuarioId, Integer qtdLikes) {
		this.id = id;
		this.legenda = legenda;
		this.setTipo(tipo);
		this.nomeUsuario = usuario.getNomeUsuario();
		this.usuarioId = usuario.getId();
		this.qtdLikes = qtdLikes;
	}
	
	public PostDTO(Post entity) {
		this.id = entity.getId();
		this.legenda = entity.getLegenda();
		this.comentarios = entity.getComentarios();
		this.tipo = entity.getTipo();
		this.qtdLikes = entity.getQtdLikes();
		this.likes = entity.getLikes();
	}

	public static PostDTO from(Post post) {
		return new PostDTO(post);
	}
	
	public Post to(Usuario usuario) {
		return new Post(this.id,this.legenda,this.tipo, usuario.getId());
	
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
		//return usuario.getNomeUsuario();
		return nomeUsuario;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeUsuario == null) ? 0 : nomeUsuario.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostDTO other = (PostDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeUsuario == null) {
			if (other.nomeUsuario != null)
				return false;
		} else if (!nomeUsuario.equals(other.nomeUsuario))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (usuarioId == null) {
			if (other.usuarioId != null)
				return false;
		} else if (!usuarioId.equals(other.usuarioId))
			return false;
		return true;
	}

	/*public Likes getLikes() {
		return likes;
	}

	public void setLikes(Likes likes) {
		this.likes = likes;
	}*/
	
	public Integer getQtdLikes() {
		return qtdLikes;
	}

	public List<Likes> getLikes() {
		return likes;
	}

	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}
	
}

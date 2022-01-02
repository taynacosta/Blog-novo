package tayna.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tayna.domain.enun.TipoDePost;

@Entity
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String legenda;

	private TipoDePost tipo;

	private Integer idUsuario;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
	private List<Comentario> comentarios = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
	@JsonIgnore
	private List<Likes> likes = new ArrayList<>();

	private Integer qtdLikes;

	public Post() {
	}

	public Post(Integer id, String legenda, TipoDePost tipo, Integer idUsuario) {
		this.id = id;
		this.legenda = legenda;
		this.tipo = tipo;
		this.idUsuario = idUsuario;
		defineQtdLikes();
	}

	public Integer defineQtdLikes() {
		return this.qtdLikes = likes.size();
	}

	public void setQtdLikes(Integer qtdLikes) {
		this.qtdLikes = qtdLikes;
	}

	public Integer getQtdLikes() {
		return qtdLikes;
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

	public TipoDePost getTipo() {
		return tipo;
	}

	public void setTipo(TipoDePost tipo) {
		this.tipo = tipo;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Likes> getLikes() {
		return likes;
	}

	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}

}
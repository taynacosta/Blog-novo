package tayna.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String conteudo;
	
	@ManyToOne @JoinColumn(name = "post_id")
	@JsonIgnore
	private Post post;
	
	private String nomeUsuario;
	
	@OneToOne @JsonIgnore
	private Usuario usuario;
			
	public Comentario(){
	}
	
	public Comentario(Integer id, String conteudo, Post post, Usuario usuario) {
		this.id = id;
		this.conteudo = conteudo;
		this.post = post;
		this.usuario = usuario;
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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}	

	public String getNomeUsuario() {
		return this.usuario.getNomeUsuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}

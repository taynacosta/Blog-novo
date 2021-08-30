package tayna.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comentarios implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String conteudo;
	
	//@OneToOne @JoinColumn(name ="post_id", referencedColumnName = "id")
	@ManyToOne @JoinColumn(name="post_id")
	private Post post;
	
	Comentarios(){
	}

	public Comentarios(Integer id, String conteudo, Post post) {
		this.id = id;
		this.conteudo = conteudo;
		this.post = post;
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

	public Post getPost(Post post) {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
}

package tayna.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comentarios implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String conteudo;
	
	@ManyToOne @JoinColumn(name = "post_id")
	@JsonIgnore
	private Post post;
		
	private Integer postIdDTO;
	
	public Comentarios(){
	}
	
	public Comentarios(Integer id, String conteudo, Post post, Integer postIdDTO) {
		this.id = id;
		this.conteudo = conteudo;
		this.post = post;
		this.postIdDTO = postIdDTO;
	}
		
	public Comentarios(Integer id, String conteudo, Integer postIdDTO) {
		this.id = id;
		this.conteudo = conteudo;
		this.postIdDTO = postIdDTO;
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

	public Integer getPostId() {
		return postIdDTO;
	}

	public void setPostId(Integer postId) {
		this.postIdDTO = postId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Comentarios other = (Comentarios) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}

package tayna.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import tayna.domain.enun.TipoDePost;

@Entity
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String legenda;
	private TipoDePost tipo;
	
	@ManyToOne @JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "post")
	private List <Comentarios> comentarios = new ArrayList<>();
	
	public Post() {}

	public Post(Integer id, String legenda, TipoDePost tipo, Usuario usuario, List<Comentarios> comentarios) {
		this.id = id;
		this.legenda = legenda;
		this.tipo = tipo;
		this.usuario = usuario;
		this.comentarios = comentarios;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Comentarios> getComentarios(List<Comentarios> list) {
		return comentarios;
	}

	public void setComentarios(List<Comentarios> comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Post other = (Post) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}

package tayna.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tayna.domain.enun.TipoAutorizacao;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true) @JsonIgnore
	private String email;
	
	@Column(unique=true)
	private String nomeUsuario;
	
	private String senha;
	
	@OneToOne
	private Perfil perfil;

	@JsonIgnore
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="TIPOAUTORIZACAO")
	private Set<Integer> tipoAut = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="usuario")
	@JsonIgnore
	private List <Likes> likes = new ArrayList<>();

	public Usuario(){
		addTipoAut(TipoAutorizacao.ADMIN);
	}

	public Usuario(Integer id, String nomeUsuario, String senha, String email, Perfil perfil) {
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.email = email;
		this.perfil = perfil;
		addTipoAut(TipoAutorizacao.ADMIN);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public Set<TipoAutorizacao> getTipoAut() {
		return tipoAut.stream().map(x -> TipoAutorizacao.toEnum(x)).collect(Collectors.toSet());
	}

	public void addTipoAut(TipoAutorizacao tipo) {
		tipoAut.add(tipo.getCod());
	}

	public List<Likes> getLikes() {
		return likes;
	}

	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}

	public void setTipoAut(Set<Integer> tipoAut) {
		this.tipoAut = tipoAut;
	}

}

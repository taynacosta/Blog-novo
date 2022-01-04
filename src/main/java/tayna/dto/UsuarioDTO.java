package tayna.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tayna.domain.Perfil;
import tayna.domain.Usuario;
import tayna.services.validation.UsuarioInsert;

@UsuarioInsert
public class UsuarioDTO  implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@Column(unique=true)
	@NotEmpty(message= "Preenhimento obrigatório")
	@Length(min= 5, max= 80, message= "o tamanho deve ser entre 5 e 80 caracteres")
	private String nomeUsuario;
	
	@JsonIgnore
	@NotEmpty(message="Preenchimento obrigatório")
	private String senha;
	
	private String email;
	
	private Perfil perfil;
	
	public UsuarioDTO(Usuario usuario) {	
	}
	public UsuarioDTO() {	
	}
	
	public Usuario to() {
		return new Usuario(null, this.nomeUsuario, this.senha, this.email, this.perfil);
	}

	public UsuarioDTO(Integer id, String nomeUsuario, String senha, String email, Perfil perfil) {
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.email = email;
		this.perfil = perfil;
	}

	//construtor para o get
	public UsuarioDTO(Integer id, String nomeUsuario, String email, Perfil perfil) {
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.perfil = perfil;
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
	
	public static UsuarioDTO from(Usuario usuario) {
		return new UsuarioDTO(usuario);
	}
}

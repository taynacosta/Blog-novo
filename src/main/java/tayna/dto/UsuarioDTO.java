package tayna.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private int senha;
	
	//@Email @NotNull(message="O campo email é de preenchimento obrigatório")
	private String email;
	
	public UsuarioDTO(Usuario obj) {	
	}
	public UsuarioDTO() {	
	}
	
	public Usuario to() {
		return new Usuario(null, this.nomeUsuario, this.senha, this.email);
	}

	public UsuarioDTO(Integer id, String nomeUsuario, int senha, String email) {
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.email = email;
	}

	//construtor para o get
	public UsuarioDTO(Integer id, String nomeUsuario, String email) {
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
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

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static UsuarioDTO from(Usuario usuario) {
		return new UsuarioDTO(usuario);
	}

}

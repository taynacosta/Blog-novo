package tayna.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class UsuarioDTO  implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message= "Preenhimento obrigatório")
	@Length(min= 5, max= 80, message= "o tamanho deve ser entre 5 e 80 caracteres")
	private String nomeUsuario;
	
	private int senha;
	
	@Email @NotNull(message="O campo email é de preenchimento obrigatório")
	private String email;
	
	public UsuarioDTO() {
		
	}

	public UsuarioDTO(Integer id, String nomeUsuario, int senha, String email) {
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
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
	
}

package tayna.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

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
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String senha;
	
	private String email;
	
	private Perfil perfil;

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
	
	public UsuarioDTO(Usuario entity) {
		this.id = entity.getId();
		this.nomeUsuario = entity.getNomeUsuario();
		this.senha = entity.getSenha();
		this.email = entity.getEmail();
		this.perfil = entity.getPerfil();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeUsuario == null) ? 0 : nomeUsuario.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
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
		UsuarioDTO other = (UsuarioDTO) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
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
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		return true;
	}
	
}

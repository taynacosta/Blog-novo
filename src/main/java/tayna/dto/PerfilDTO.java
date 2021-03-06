package tayna.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import tayna.domain.Perfil;
import tayna.domain.enun.Genero;
import tayna.domain.enun.StatusCivil;

public class PerfilDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private StatusCivil statusCivil;
	
	@NotEmpty(message= "Preenhimento obrigatório")
	private Genero genero;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dataNascimento;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Integer idade;
	
	private String resumo;
	
	private Double legal;
	
	private Double confiavel;
	
	private Double sexy;
	
	@NotEmpty(message= "Preenhimento obrigatório")
	@Length(min= 3, max= 20, message= "o tamanho deve ser entre 3 e 20 caracteres")
	private String nome;
	
	private String sobrenome;
	
	public PerfilDTO() {}
	
	public PerfilDTO(Perfil perfil) {}
	
	public Perfil to() {
		return new Perfil(this.statusCivil, this.genero, this.dataNascimento,
			 this.resumo, this.legal, this.confiavel, this.sexy, this.nome, this.sobrenome);
	}

	public PerfilDTO( StatusCivil statusCivil,
			@NotEmpty(message = "Preenhimento obrigatório") Genero genero, Date dataNascimento,
			String resumo, Double legal, Double confiavel, Double sexy, String nome, String sobrenome) {
		
		this.statusCivil = statusCivil;
		this.genero = genero;
		this.dataNascimento = dataNascimento;
		this.resumo = resumo;
		this.legal = legal;
		this.confiavel = confiavel;
		this.sexy = sexy;
		this.nome = nome;
		this.sobrenome = sobrenome;
	}
	
	public static PerfilDTO from(Perfil perfil) {
		return new PerfilDTO(perfil);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StatusCivil getStatusCivil() {
		return statusCivil;
	}

	public void setStatusCivil(StatusCivil statusCivil) {
		this.statusCivil = statusCivil;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getIdade() {
		GregorianCalendar hj = new GregorianCalendar();
		GregorianCalendar dataNascimento = new GregorianCalendar();
		if (getDataNascimento() != null) {
			dataNascimento.setTime(getDataNascimento());
		}
		int anohj = hj.get(Calendar.YEAR);
		int anoNascimento = dataNascimento.get(Calendar.YEAR);
		return new Integer(anohj - anoNascimento);
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
}

package tayna.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import tayna.domain.enun.Genero;
import tayna.domain.enun.StatusCivil;

@Entity
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private StatusCivil statusCivil;
	
	private Genero genero;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dataNascimento;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Integer idade;
	
	private String resumo;
	
	private Double legal;
	
	private Double confiavel;
	
	private Double sexy;
	
	@JsonIgnore
	private String nome;
	
	@JsonIgnore
	private String sobrenome;
	
	public Perfil() {}

	public Perfil(StatusCivil statusCivil, Genero genero, Date dataNascimento,
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

	@JsonProperty(access = Access.WRITE_ONLY)
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public Double getLegal() {
		return legal;
	}

	public void setLegal(Double legal) {
		this.legal = legal;
	}

	public Double getConfiavel() {
		return confiavel;
	}

	public void setConfiavel(Double confiavel) {
		this.confiavel = confiavel;
	}

	public Double getSexy() {
		return sexy;
	}

	public void setSexy(Double sexy) {
		this.sexy = sexy;
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

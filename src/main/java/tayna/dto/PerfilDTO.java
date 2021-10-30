package tayna.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import tayna.domain.Perfil;
import tayna.domain.Usuario;
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
	
	//private Usuario usuario;
	
	private String resumo;
	
	private Double legal;
	
	private Double confiavel;
	
	private Double sexy;
	
	public PerfilDTO() {}
	
	public PerfilDTO(Perfil perfil) {}
	
	public Perfil to() {
		return new Perfil(this.statusCivil, this.genero, this.dataNascimento,
			 this.resumo, this.legal, this.confiavel, this.sexy);
	}

	public PerfilDTO( StatusCivil statusCivil,
			@NotEmpty(message = "Preenhimento obrigatório") Genero genero, Date dataNascimento,
			String resumo, Double legal, Double confiavel, Double sexy) {
		
		this.statusCivil = statusCivil;
		this.genero = genero;
		this.dataNascimento = dataNascimento;
		this.resumo = resumo;
		this.legal = legal;
		this.confiavel = confiavel;
		this.sexy = sexy;
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
	
/*	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}*/

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
	
}

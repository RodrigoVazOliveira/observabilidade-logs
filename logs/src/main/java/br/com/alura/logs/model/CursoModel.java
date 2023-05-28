package br.com.alura.logs.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CURSO")
public class CursoModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable=false, unique=true, length=10)
	private String numeroMatricula;
	
	@Column(nullable=false, unique=true, length=10)
	private String numeroCurso;
	
	@Column(nullable=false, length=120)
	private String nomeCurso;
	
	@Column(nullable=false, length=120)
	private String categoriaCurso;
	
	@Column(nullable=false, length=120)
	private String preRequisito;
	
	@Column(nullable=false, length=120)
	private String nomeProfessor;

	@Column(nullable=false, length=30)
	private String periodoCurso;
	
	@Column(nullable=false)
	private LocalDateTime dataInscricao;
	
	public String getPeriodoCurso() {
		return periodoCurso;
	}

	public void setPeriodoCurso(String periodoCurso) {
		this.periodoCurso = periodoCurso;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNumeroMatricula() {
		return numeroMatricula;
	}

	public void setNumeroMatricula(String numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}

	public String getNumeroCurso() {
		return numeroCurso;
	}

	public void setNumeroCurso(String numeroCurso) {
		this.numeroCurso = numeroCurso;
	}

	public LocalDateTime getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(LocalDateTime dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getCategoriaCurso() {
		return categoriaCurso;
	}

	public void setCategoriaCurso(String categoriaCurso) {
		this.categoriaCurso = categoriaCurso;
	}

	public String getPreRequisito() {
		return preRequisito;
	}

	public void setPreRequisito(String preRequisito) {
		this.preRequisito = preRequisito;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

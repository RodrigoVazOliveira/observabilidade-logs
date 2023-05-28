package br.com.alura.logs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CursoDto {

	@NotBlank
	@Size(max = 10)
	private String numeroMatricula;

	@NotBlank
	@Size(max = 10)
	private String numeroCurso;

	@NotBlank
	private String nomeCurso;

	@NotBlank
	private String categoriaCurso;

	@NotBlank
	private String preRequisito;

	@NotBlank
	private String nomeProfessor;

	@NotBlank
	private String periodoCurso;

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

	public String getPeriodoCurso() {
		return periodoCurso;
	}

	public void setPeriodoCurso(String periodoCurso) {
		this.periodoCurso = periodoCurso;
	}

	@Override
	public String toString() {
		final ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}

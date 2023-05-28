package br.com.alura.logs.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.logs.model.CursoModel;

@Repository
public interface CursoRepository extends JpaRepository<CursoModel, UUID> {

	boolean existsByNumeroMatricula(String numeroMatricula);

	boolean existsByNumeroCurso(String numeroCurso);

}

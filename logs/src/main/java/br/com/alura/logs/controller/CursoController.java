package br.com.alura.logs.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.logs.dto.CursoDto;
import br.com.alura.logs.exceptions.InternalErrorException;
import br.com.alura.logs.model.CursoModel;
import br.com.alura.logs.service.CursoService;
import net.logstash.logback.argument.StructuredArguments;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cursos")
public class CursoController {

	private final CursoService cursoService;
	private final static Logger LOGGER = LoggerFactory.getLogger(CursoController.class);

	public CursoController(CursoService cursoService) {
		this.cursoService = cursoService;
	}

	@PostMapping
	public ResponseEntity<Object> saveCurso(@RequestBody @Valid CursoDto cursoDto) {
		LOGGER.info("Iniciando salvamento de um curso. {}", StructuredArguments.keyValue("cursoDto", cursoDto));

		if (cursoService.existsByNumeroMatricula(cursoDto.getNumeroMatricula())) {
			LOGGER.warn("Novo registro não gravado, número de matricula já existe. {}",
					StructuredArguments.keyValue("numeroMatricula", cursoDto.getNumeroMatricula()));
			return ResponseEntity.status(HttpStatus.CONFLICT).body("O número de matricula do curso já esta em uso!");
		}

		if (cursoService.existsByNumeroCurso(cursoDto.getNumeroCurso())) {
			LOGGER.warn("Novo registro não gravada. O número do curso já existe. {}",
					StructuredArguments.keyValue("numeroCurso", cursoDto.getNumeroCurso()));
			return ResponseEntity.status(HttpStatus.CONFLICT).body("O número do curso já esta em uso!");
		}

		LOGGER.info("Validações de cursoService sobre cursoDto executada com sucesso!");

		CursoModel cursoModel = new CursoModel();
		BeanUtils.copyProperties(cursoDto, cursoModel);
		cursoModel.setDataInscricao(LocalDateTime.now(ZoneId.of("UTC")));

		LOGGER.info("Um novo registro foi salvo com sucesso!");
		return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(cursoModel));
	}

	@GetMapping
	public ResponseEntity<Page<CursoModel>> getAllCursos(
			@PageableDefault(page = 0, size = 10, sort = "dataInscricao", direction = Sort.Direction.ASC) Pageable pageable) {
		LOGGER.info("Buscando todos os registros de curso");
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cursoService.findAll(pageable));
		} catch (CannotCreateTransactionException exception) {
			LOGGER.error("Erro na comunicação do banco de dados. {}",
					StructuredArguments.keyValue("message", exception.getMessage()));
			throw new InternalErrorException("Erro momentâneo, por favor tente mais tarde...");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneCursos(@PathVariable(value = "id") UUID id) {
		LOGGER.info("Chamando cursoService para buscar um registro por UUID");
		Optional<CursoModel> cursoModelOptional = cursoService.findById(id);
		if (!cursoModelOptional.isPresent()) {
			LOGGER.info("Validação no cursoService não encontrou um registro procurado. {}",
					StructuredArguments.keyValue("id", id));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado!");
		}

		LOGGER.info("O registro procurado pelo cliente foi encontrado pelo cursoService no banco de dados");
		return ResponseEntity.status(HttpStatus.OK).body(cursoModelOptional.get());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCursos(@PathVariable(value = "id") UUID id) {
		Optional<CursoModel> cursoModelOptional = cursoService.findById(id);
		if (!cursoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado!");
		}
		cursoService.delete(cursoModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Curso excluído com sucesso!");
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCursos(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid CursoDto cursoDto) {
		Optional<CursoModel> cursoModelOptional = cursoService.findById(id);
		if (!cursoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado!");
		}
		var cursoModel = new CursoModel();
		BeanUtils.copyProperties(cursoDto, cursoModel);
		cursoModel.setId(cursoModelOptional.get().getId());
		cursoModel.setDataInscricao(cursoModelOptional.get().getDataInscricao());
		return ResponseEntity.status(HttpStatus.OK).body(cursoService.save(cursoModel));
	}

}

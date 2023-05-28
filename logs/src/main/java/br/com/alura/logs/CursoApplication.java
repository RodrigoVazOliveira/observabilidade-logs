package br.com.alura.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursoApplication {
	private final static Logger LOGGER = LoggerFactory.getLogger(CursoApplication.class);

	public static void main(String[] args) {
		LOGGER.info("[main] Iniciando aplicação API do curso alura");

		SpringApplication.run(CursoApplication.class, args);
		LOGGER.info("[main] Aplicação para gerenciar curso na alura");
	}
}

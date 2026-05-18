package br.com.clyvo.pet;

import br.com.clyvo.pet.util.MotorReflectionClyvo;
import br.com.clyvo.pet.entity.PacienteAnimal;
import br.com.clyvo.pet.enums.CategoriaEspecie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Criamos um objeto fictício apenas para provar o uso do Reflection
		PacienteAnimal pacienteDemonstracao = PacienteAnimal.builder()
				.idPaciente(999L)
				.apelido("Rex Reflection")
				.especie(CategoriaEspecie.CACHORRO)
				.raca("Pastor Alemão")
				.responsavelLegal("Professor FIAP")
				.peso(35.0)
				.ativo(true)
				.build();


		MotorReflectionClyvo.analisarEImprimirEstrutura(pacienteDemonstracao);
	}
}
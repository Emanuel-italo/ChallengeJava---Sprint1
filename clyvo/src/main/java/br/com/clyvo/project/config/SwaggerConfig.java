package br.com.clyvo.pet.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI clyvoVetOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Clyvo Vet API")
                        .description("API REST do ecossistema Clyvo Vet — a infraestrutura do futuro da medicina veterinária digital. " +
                                "Focada na continuidade do cuidado e engajamento na jornada de saúde do pet, permitindo o acompanhamento longitudinal, " +
                                "gestão de pacientes, registro de imunizações, rotinas preventivas e alertas preditivos para clínicas e responsáveis.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Clyvo Vet Engineering Team")
                                .url("https://github.com/Emanuel-italo/ChallengeJava---Sprint1.git"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
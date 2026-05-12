package br.com.fiap.clyvovet.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.*;

import java.util.List;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ClyvoVet API")
                        .description("""
                                **ClyvoVet API** — Challenge 2026 FIAP · 2TDS Fevereiro
                                
                                Solução para a continuidade do cuidado e engajamento na jornada de saúde do pet.
                                
                                ## Problema resolvido
                                Transforma a jornada do pet de um modelo **episódico e reativo** para uma 
                                experiência **contínua, preventiva, inteligente e integrada**.
                                
                                ## Funcionalidades principais
                                - 🐾 Gestão completa de pets e tutores
                                - 🏥 Histórico longitudinal de consultas
                                - 💊 Controle de prescrições e adesão medicamentosa
                                - 🔔 Alertas preditivos de saúde
                                - 📊 Score de risco calculado por inteligência veterinária
                                - 📋 Planos de saúde preventivos (assinatura)
                                - 🏢 Dashboard de analytics para clínicas
                                
                                ## Banco de dados
                                Use H2 Console em: http://localhost:8080/h2-console
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipe ClyvoVet - 2TDS Fevereiro")
                                .email("equipe@clyvovet.com.br"))
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Desenvolvimento Local"),
                        new Server().url("https://api.clyvovet.com.br").description("Produção")
                ));
    }
}
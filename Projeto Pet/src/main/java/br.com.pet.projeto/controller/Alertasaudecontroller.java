package br.com.fiap.clyvovet.controller;
 
import br.com.fiap.clyvovet.dto.response.AlertaSaudeResponse;
import br.com.fiap.clyvovet.entity.AlertaSaude;
import br.com.fiap.clyvovet.service.AlertaSaudeService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;


@RestController
@RequestMapping("/api/v1/alertas")
@RequiredArgsConstructor
@Tag(name = "Alertas de Saúde",
        description = "Sistema de alertas preditivos e recomendações para tutores e clínicas")
public class AlertaSaudeController {
 
    private final AlertaSaudeService alertaService;
 
    @GetMapping("/pet/{petId}")
    @Operation(summary = "Listar alertas do pet",
               description = "Retorna todos os alertas de saúde de um pet específico, paginados.")
    public ResponseEntity<Page<AlertaSaudeResponse>> listarPorPet(
            @PathVariable Long petId,
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(alertaService.listarPorPet(petId, pageable));
    }

  @GetMapping("/tutor/{tutorId}/pendentes")
    @Operation(summary = "Alertas pendentes do tutor",
               description = "Retorna todos os alertas pendentes e não lidos do tutor, " +
                             "ordenados por prioridade. Ideal para notificação push/WhatsApp.")
    public ResponseEntity<List<AlertaSaudeResponse>> alertasPendentes(@PathVariable Long tutorId) {
        return ResponseEntity.ok(alertaService.alertasPendentesByTutor(tutorId));
    }
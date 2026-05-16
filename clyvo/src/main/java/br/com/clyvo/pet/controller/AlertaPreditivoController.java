package br.com.clyvo.pet.controller;

import br.com.clyvo.pet.dto.AlertaPreditivoRequestDTO;
import br.com.clyvo.pet.dto.AlertaPreditivoResponseDTO;
import br.com.clyvo.pet.service.AlertaPreditivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas")
@RequiredArgsConstructor
@Tag(name = "Alertas Preditivos", description = "Gerenciamento de avisos inteligentes e contínuos para a jornada de saúde do paciente")
public class AlertaPreditivoController {

    private final AlertaPreditivoService alertaService;

    @GetMapping
    @Operation(summary = "Listar todos os alertas preditivos registrados")
    public ResponseEntity<List<AlertaPreditivoResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(alertaService.buscarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar alerta preditivo específico por identificador")
    public ResponseEntity<AlertaPreditivoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alertaService.buscarPorId(id));
    }

    @GetMapping("/pendentes")
    @Operation(summary = "Listar todos os alertas pendentes de ação ou envio")
    public ResponseEntity<List<AlertaPreditivoResponseDTO>> buscarPendentes() {
        return ResponseEntity.ok(alertaService.buscarTodosPendentes());
    }

    @GetMapping("/paciente/{pacienteId}")
    @Operation(summary = "Listar histórico completo de alertas de um paciente (pet)")
    public ResponseEntity<List<AlertaPreditivoResponseDTO>> buscarPorPacienteId(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(alertaService.buscarPorPacienteId(pacienteId));
    }

    @GetMapping("/paciente/{pacienteId}/pendentes")
    @Operation(summary = "Listar apenas alertas pendentes de um paciente")
    public ResponseEntity<List<AlertaPreditivoResponseDTO>> buscarPendentesPorPacienteId(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(alertaService.buscarPendentesPorPacienteId(pacienteId));
    }

    @PostMapping
    @Operation(summary = "Registrar novo alerta preditivo ou agendamento clínico")
    public ResponseEntity<AlertaPreditivoResponseDTO> registrarAlerta(@Valid @RequestBody AlertaPreditivoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alertaService.registrar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados de um alerta preditivo existente")
    public ResponseEntity<AlertaPreditivoResponseDTO> atualizarAlerta(@PathVariable Long id, @Valid @RequestBody AlertaPreditivoRequestDTO dto) {
        return ResponseEntity.ok(alertaService.atualizar(id, dto));
    }

    @PatchMapping("/{id}/marcar-enviado")
    @Operation(summary = "Sinalizar que o alerta preditivo foi despachado com sucesso ao responsável")
    public ResponseEntity<AlertaPreditivoResponseDTO> sinalizarEnvio(@PathVariable Long id) {
        return ResponseEntity.ok(alertaService.marcarComoEnviado(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover alerta preditivo do sistema")
    public ResponseEntity<Void> removerAlerta(@PathVariable Long id) {
        alertaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
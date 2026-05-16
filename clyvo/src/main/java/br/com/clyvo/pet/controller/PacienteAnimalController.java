package br.com.clyvo.pet.controller;

import br.com.clyvo.pet.dto.PacienteHistoricoDTO;
import br.com.clyvo.pet.dto.PacienteRequestDTO;
import br.com.clyvo.pet.dto.PacienteResponseDTO;
import br.com.clyvo.pet.enums.CategoriaEspecie;
import br.com.clyvo.pet.service.PacienteAnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
@Tag(name = "Pacientes", description = "Gerenciamento e acompanhamento longitudinal dos pacientes animais")
public class PacienteAnimalController {

    private final PacienteAnimalService pacienteService;

    @GetMapping
    @Operation(summary = "Listar todos os pacientes ativos no sistema")
    public ResponseEntity<List<PacienteResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(pacienteService.buscarTodosAtivos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar paciente por ID")
    public ResponseEntity<PacienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @GetMapping("/busca")
    @Operation(summary = "Buscar pacientes por termo contido no nome/apelido")
    public ResponseEntity<List<PacienteResponseDTO>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(pacienteService.buscarPorNome(nome));
    }

    @GetMapping("/especie/{especie}")
    @Operation(summary = "Filtrar pacientes por categoria de espécie")
    public ResponseEntity<List<PacienteResponseDTO>> buscarPorEspecie(@PathVariable CategoriaEspecie especie) {
        return ResponseEntity.ok(pacienteService.buscarPorEspecie(especie));
    }

    @GetMapping("/imunizacoes/vencendo")
    @Operation(summary = "Listar pacientes com imunizações vencidas ou próximas do vencimento crítico")
    public ResponseEntity<List<PacienteResponseDTO>> buscarPacientesComImunizacaoPendente() {
        return ResponseEntity.ok(pacienteService.buscarPacientesComImunizacoesCriticas());
    }

    @GetMapping("/{id}/historico-longitudinal")
    @Operation(summary = "Histórico clínico longitudinal consolidado: imunizações, atividades e alertas preditivos")
    public ResponseEntity<PacienteHistoricoDTO> obterHistoricoClinico(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.obterHistoricoLongitudinal(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo paciente no ecossistema clínico")
    public ResponseEntity<PacienteResponseDTO> cadastrarPaciente(@Valid @RequestBody PacienteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.criar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados cadastrais e clínicos do paciente")
    public ResponseEntity<PacienteResponseDTO> atualizarPaciente(@PathVariable Long id, @Valid @RequestBody PacienteRequestDTO dto) {
        return ResponseEntity.ok(pacienteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Inativar paciente do sistema (Soft Delete para preservação de histórico)")
    public ResponseEntity<Void> inativarPaciente(@PathVariable Long id) {
        pacienteService.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
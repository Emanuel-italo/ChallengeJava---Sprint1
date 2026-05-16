package br.com.clyvo.pet.controller;



import br.com.clyvo.pet.dto.RegistroImunologicoRequestDTO;
import br.com.clyvo.pet.dto.RegistroImunologicoResponseDTO;
import br.com.clyvo.pet.service.RegistroImunologicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/imunizacoes")
@RequiredArgsConstructor
@Tag(name = "Registros Imunológicos", description = "Gerenciamento de vacinas e imunizações do paciente")
public class RegistroImunologicoController {

    private final RegistroImunologicoService registroService;

    @GetMapping
    @Operation(summary = "Listar todos os registros de imunização")
    public ResponseEntity<List<RegistroImunologicoResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(registroService.buscarTodos());
    }
}

@GetMapping("/{id}")
@Operation(summary = "Buscar registro de imunização por ID")
public ResponseEntity<RegistroImunologicoResponseDTO> buscarPorId(@PathVariable Long id) {
    return ResponseEntity.ok(registroService.buscarPorId(id));
}

@GetMapping("/paciente/{pacienteId}")
@Operation(summary = "Listar imunizações de um paciente específico")
public ResponseEntity<List<RegistroImunologicoResponseDTO>> buscarPorPacienteId(@PathVariable Long pacienteId) {
    return ResponseEntity.ok(registroService.buscarPorPacienteId(pacienteId));
}

@PostMapping
@Operation(summary = "Registrar nova aplicação de vacina")
public ResponseEntity<RegistroImunologicoResponseDTO> registrar(@Valid @RequestBody RegistroImunologicoRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(registroService.registrar(dto));
}

@PutMapping("/{id}")
@Operation(summary = "Atualizar registro de imunização existente")
public ResponseEntity<RegistroImunologicoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody RegistroImunologicoRequestDTO dto) {
    return ResponseEntity.ok(registroService.atualizar(id, dto));
}

@DeleteMapping("/{id}")
@Operation(summary = "Remover registro de imunização")
public ResponseEntity<Void> remover(@PathVariable Long id) {
    registroService.remover(id);
    return ResponseEntity.noContent().build();
}
}
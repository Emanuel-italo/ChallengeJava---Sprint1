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
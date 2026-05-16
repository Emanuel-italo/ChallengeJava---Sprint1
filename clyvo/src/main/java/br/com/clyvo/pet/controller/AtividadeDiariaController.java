package br.com.clyvo.pet.controller;

import br.com.clyvo.pet.dto.AtividadeDiariaRequestDTO;
import br.com.clyvo.pet.dto.AtividadeDiariaResponseDTO;
import br.com.clyvo.pet.service.AtividadeDiariaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Atividades de Rotina", description = "Gerenciamento de cuidados diários e preventivos do paciente")
public class AtividadeDiariaController {

    private final AtividadeDiariaService atividadeService;

    @GetMapping("/rotinas")
    @Operation(summary = "Listar todos os registros de atividades diárias")
    public ResponseEntity<List<AtividadeDiariaResponseDTO>> buscarTodas() {
        return ResponseEntity.ok(atividadeService.buscarTodas());
    }

    @GetMapping("/rotinas/{id}")
    @Operation(summary = "Buscar registro de atividade por ID")
    public ResponseEntity<AtividadeDiariaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(atividadeService.buscarPorId(id));
    }
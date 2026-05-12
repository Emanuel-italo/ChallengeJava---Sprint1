package br.com.fiap.clyvovet.controller;

import br.com.fiap.clyvovet.dto.request.TutorRequest;
import br.com.fiap.clyvovet.dto.response.TutorResponse;
import br.com.fiap.clyvovet.entity.Tutor;
import br.com.fiap.clyvovet.service.TutorService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/tutores")
@RequiredArgsConstructor
@Tag(name = "Tutores", description = "Gerenciamento de tutores (responsáveis pelos pets)")
public class TutorController {
 
    private final TutorService tutorService;
 
    // ---- POST /api/v1/tutores ----
 
    @PostMapping
    @Operation(summary = "Cadastrar tutor",
               description = "Cria um novo tutor (responsável pelo pet) no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tutor criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "E-mail ou CPF já cadastrado")
    })
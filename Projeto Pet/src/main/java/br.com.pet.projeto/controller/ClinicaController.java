package br.com.fiap.clyvovet.controller;
 
import br.com.fiap.clyvovet.dto.request.ClinicaRequest;
import br.com.fiap.clyvovet.dto.response.ClinicaResponse;
import br.com.fiap.clyvovet.entity.Clinica;
import br.com.fiap.clyvovet.service.ClinicaService;
import io.swagger.v3.oas.annotations.*;
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
@RequestMapping("/api/v1/clinicas")
@RequiredArgsConstructor
@Tag(name = "Clínicas", description = "Gerenciamento de clínicas veterinárias parceiras")
public class ClinicaController {
 
    private final ClinicaService clinicaService;
 
    @PostMapping
    @Operation(summary = "Cadastrar clínica")
    @ApiResponse(responseCode = "201", description = "Clínica criada")
    public ResponseEntity<ClinicaResponse> criar(@Valid @RequestBody ClinicaRequest request) {
        ClinicaResponse response = clinicaService.criar(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(location).body(response);
    }
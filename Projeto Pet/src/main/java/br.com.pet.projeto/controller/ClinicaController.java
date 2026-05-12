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

@GetMapping("/{id}")
    @Operation(summary = "Buscar clínica por ID")
    public ResponseEntity<ClinicaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clinicaService.buscarPorId(id));
    }
 
    @GetMapping
    @Operation(summary = "Listar clínicas com filtros opcionais")
    public ResponseEntity<Page<ClinicaResponse>> listar(
            @RequestParam(required = false) Clinica.StatusClinica status,
            @RequestParam(required = false) String termo,
            @ParameterObject Pageable pageable) {
 
        if (termo != null && !termo.isBlank()) {
            return ResponseEntity.ok(clinicaService.buscarPorTermo(termo, pageable));
        } else if (status != null) {
            return ResponseEntity.ok(clinicaService.listarPorStatus(status, pageable));
        }
        return ResponseEntity.ok(clinicaService.listarTodas(pageable));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar clínica")
    public ResponseEntity<ClinicaResponse> atualizar(
            @PathVariable Long id, @Valid @RequestBody ClinicaRequest request) {
        return ResponseEntity.ok(clinicaService.atualizar(id, request));
    }
 
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir clínica")
    @ApiResponse(responseCode = "204", description = "Clínica excluída")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        clinicaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
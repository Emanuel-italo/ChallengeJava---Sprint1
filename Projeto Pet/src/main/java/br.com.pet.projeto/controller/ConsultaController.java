package br.com.fiap.clyvovet.controller;
 
import br.com.fiap.clyvovet.dto.request.ConsultaRequest;
import br.com.fiap.clyvovet.dto.response.ConsultaResponse;
import br.com.fiap.clyvovet.entity.Consulta;
import br.com.fiap.clyvovet.service.ConsultaService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
 
import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/consultas")
@RequiredArgsConstructor
@Tag(name = "Consultas", description = "Agendamento e histórico de consultas veterinárias")
public class ConsultaController {
 
    private final ConsultaService consultaService;
 
    @PostMapping
    @Operation(summary = "Agendar / registrar consulta")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Consulta criada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Pet ou clínica não encontrado"),
            @ApiResponse(responseCode = "422", description = "Regra de negócio violada (ex: clínica inativa)")
    })
    public ResponseEntity<ConsultaResponse> criar(@Valid @RequestBody ConsultaRequest request) {
        ConsultaResponse response = consultaService.criar(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(location).body(response);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar consulta por ID")
    public ResponseEntity<ConsultaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarPorId(id));
    }
 
    @GetMapping
    @Operation(summary = "Listar consultas",
               description = "Lista consultas com filtros opcionais por tipo, status, pet, clínica e período.")
    public ResponseEntity<Page<ConsultaResponse>> listar(
            @RequestParam(required = false) Consulta.TipoConsulta tipo,
            @RequestParam(required = false) Consulta.StatusConsulta status,
            @RequestParam(required = false) Long petId,
            @RequestParam(required = false) Long clinicaId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim,
            @ParameterObject Pageable pageable) {
 
        Page<ConsultaResponse> page;
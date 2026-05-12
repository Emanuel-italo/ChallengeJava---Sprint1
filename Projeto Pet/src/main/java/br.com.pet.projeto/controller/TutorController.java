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
 

 
    @PostMapping
    @Operation(summary = "Cadastrar tutor",
               description = "Cria um novo tutor (responsável pelo pet) no sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tutor criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "E-mail ou CPF já cadastrado")
    })

        public ResponseEntity<TutorResponse> criar(@Valid @RequestBody TutorRequest request) {
        TutorResponse response = tutorService.criar(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }
 
     // ---- GET /api/v1/tutores/{id} ----
 
    @GetMapping("/{id}")
    @Operation(summary = "Buscar tutor por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tutor encontrado"),
            @ApiResponse(responseCode = "404", description = "Tutor não encontrado")
    })
    public ResponseEntity<TutorResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tutorService.buscarPorId(id));
    }
 
    // ---- GET /api/v1/tutores ----
    
    @GetMapping
        @Operation(summary = "Listar tutores",
                description = "Lista todos os tutores com suporte a paginação e ordenação.")
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
        public ResponseEntity<Page<TutorResponse>> listar(
                @RequestParam(required = false) String nome,
                @RequestParam(required = false) Tutor.StatusTutor status,
                @ParameterObject Pageable pageable) {
    
            Page<TutorResponse> page;

    if (nome != null && !nome.isBlank()) {
            page = tutorService.buscarPorTermo(nome, pageable);
        } else if (status != null) {
            page = tutorService.buscarPorStatus(status, pageable);
        } else {
            page = tutorService.listarTodos(pageable);
        }
 
        return ResponseEntity.ok(page);
    }
 
    // ---- GET /api/v1/tutores/busca?termo= ----
 
    @GetMapping("/busca")
    @Operation(summary = "Busca full-text por nome ou e-mail do tutor")
    public ResponseEntity<Page<TutorResponse>> buscar(
            @RequestParam String termo,
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(tutorService.buscarPorTermo(termo, pageable));
    }

       // ---- PUT /api/v1/tutores/{id} ----
 
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tutor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tutor atualizado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Tutor não encontrado"),
            @ApiResponse(responseCode = "409", description = "E-mail já cadastrado")
    })
    public ResponseEntity<TutorResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody TutorRequest request) {
        return ResponseEntity.ok(tutorService.atualizar(id, request));
    }
 

 // ---- DELETE /api/v1/tutores/{id} ----
 
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir tutor")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tutor excluído"),
            @ApiResponse(responseCode = "404", description = "Tutor não encontrado")
    })
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        tutorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
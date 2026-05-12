package br.com.fiap.clyvovet.controller;
 
import br.com.fiap.clyvovet.dto.request.PetRequest;
import br.com.fiap.clyvovet.dto.response.PetResponse;
import br.com.fiap.clyvovet.entity.Pet;
import br.com.fiap.clyvovet.service.PetService;
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
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
@Tag(name = "Pets", description = "Gerenciamento de pets e seus dados de saúde")
public class PetController {
 
    private final PetService petService;
 
    @PostMapping
    @Operation(summary = "Cadastrar pet")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pet cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Tutor não encontrado")
    })
    public ResponseEntity<PetResponse> criar(@Valid @RequestBody PetRequest request) {
        PetResponse response = petService.criar(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(location).body(response);
    }
 
    @GetMapping("/{id}")
    @Operation(summary = "Buscar pet por ID")
    public ResponseEntity<PetResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(petService.buscarPorId(id));
    }
 
    @GetMapping
    @Operation(summary = "Listar pets",
               description = "Lista pets com filtros opcionais por espécie, status de saúde e termo de busca. " +
                             "Suporta paginação (?page=0&size=10) e ordenação (?sort=nome,asc).")
    public ResponseEntity<Page<PetResponse>> listar(
            @RequestParam(required = false) Pet.Especie especie,
            @RequestParam(required = false) Pet.StatusSaude statusSaude,
            @RequestParam(required = false) Long tutorId,
            @RequestParam(required = false) String termo,
            @ParameterObject Pageable pageable) {
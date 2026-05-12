    package br.com.fiap.clyvovet.controller;

    import br.com.fiap.clyvovet.dto.response.PetResponse;
    import br.com.fiap.clyvovet.service.PetService;
    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import lombok.RequiredArgsConstructor;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/v1/pets")
    @RequiredArgsConstructor
    @Tag(name = "Pets Analytics")
    public class PetController {

        private final PetService petService;

        @GetMapping
        @Operation(summary = "Lista paginada de pets")
        public ResponseEntity<Page<PetResponse>> listarPets(Pageable pageable) {
            return ResponseEntity.ok(petService.listarTodos(pageable));
        }

        @PostMapping("/{id}/analise-preditiva")
        @Operation(summary = "Gera score de risco usando Reflection e cruza dados do DAO")
        public ResponseEntity<Integer> gerarScoreRisco(@PathVariable Long id) {
            return ResponseEntity.ok(petService.calcularRiscoInteligente(id));
        }
    }
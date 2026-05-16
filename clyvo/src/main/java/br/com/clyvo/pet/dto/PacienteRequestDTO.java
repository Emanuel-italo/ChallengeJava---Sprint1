package br.com.clyvo.pet.dto;

import br.com.clyvo.pet.enums.CategoriaEspecie;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteRequestDTO {

    @NotBlank(message = "O apelido do paciente é obrigatório")
    @Size(min = 2, max = 100, message = "O apelido deve ter entre 2 e 100 caracteres")
    private String apelido;

    @NotNull(message = "A categoria da espécie é obrigatória")
    private CategoriaEspecie especie;

    @Size(max = 100, message = "A raça deve ter no máximo 100 caracteres")
    private String raca;

    @Past(message = "A data de nascimento deve ser uma data passada")
    private LocalDate dataNascimento;

    @Positive(message = "O peso deve ser um valor positivo")
    private Double peso;

    @NotBlank(message = "O nome do responsável legal é obrigatório")
    @Size(max = 100, message = "O nome do responsável deve ter no máximo 100 caracteres")
    private String responsavelLegal;

    @Size(max = 20, message = "Telefone deve ter no máximo 20 caracteres")
    private String tutorPhone;
}


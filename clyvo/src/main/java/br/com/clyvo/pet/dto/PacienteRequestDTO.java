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

    @Size(max = 100, message = "Raça deve ter no máximo 100 caracteres")
    private String breed;

    @Past(message = "Data de nascimento deve ser no passado")
    private LocalDate birthDate;

    @Positive(message = "Peso deve ser positivo")
    private Double weight;

    @NotBlank(message = "Nome do tutor é obrigatório")
    @Size(max = 100, message = "Nome do tutor deve ter no máximo 100 caracteres")
    private String tutorName;

    @Size(max = 20, message = "Telefone deve ter no máximo 20 caracteres")
    private String tutorPhone;
}


package br.com.clyvo.pet.dto;

import br.com.clyvo.pet.enums.StatusImunizacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroImunologicoRequestDTO {

    @NotNull(message = "O identificador do paciente é obrigatório")
    private Long pacienteId;
}
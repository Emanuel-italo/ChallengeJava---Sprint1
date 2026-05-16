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

    @NotBlank(message = "O nome do imunizante é obrigatório")
    @Size(max = 100, message = "O nome da vacina deve conter no máximo 100 caracteres")
    private String nomeVacina;

    @Size(max = 50, message = "O lote deve conter no máximo 50 caracteres")
    private String lote;

    @NotNull(message = "A data de aplicação é obrigatória")
    private LocalDate dataAplicacao;
}
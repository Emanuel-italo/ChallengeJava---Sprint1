package br.com.clyvo.pet.dto;

import br.com.clyvo.pet.enums.TipoRotina;
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
public class AtividadeDiariaRequestDTO {

    @NotNull(message = "O identificador do paciente é obrigatório")
    private Long pacienteId;
    @NotNull(message = "O tipo de atividade de rotina é obrigatório")
    private TipoRotina tipo;

    @Size(max = 500, message = "A descrição deve conter no máximo 500 caracteres")
    private String descricao;

    @NotNull(message = "A data do registro é obrigatória")
    private LocalDate dataRegistro;
}
package br.com.clyvo.pet.dto;

import br.com.clyvo.pet.enums.TipoAlertaPreditivo;
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
public class AlertaPreditivoRequestDTO {

    @NotNull(message = "O identificador do paciente é obrigatório")
    private Long pacienteId;

    // Correção: mudado de 'type' para 'tipo'
    @NotNull(message = "O tipo de alerta preditivo é obrigatório")
    private TipoAlertaPreditivo tipo;

    @NotBlank(message = "A mensagem de alerta é obrigatória")
    @Size(max = 500, message = "A mensagem deve conter no máximo 500 caracteres")
    private String mensagem;

    private LocalDate dataPrevista;
}
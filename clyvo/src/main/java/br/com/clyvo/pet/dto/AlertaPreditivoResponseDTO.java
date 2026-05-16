package br.com.clyvo.pet.dto;

import br.com.clyvo.pet.enums.TipoAlertaPreditivo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlertaPreditivoResponseDTO {

    private Long id;
    private Long pacienteId;
    private String nomePaciente;
    private TipoAlertaPreditivo tipo;
    private String mensagem;
    private LocalDate dataPrevista;
    private Boolean enviado;
    private LocalDateTime dataCriacao;
}
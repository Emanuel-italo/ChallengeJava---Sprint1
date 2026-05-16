package br.com.clyvo.pet.dto;

import br.com.clyvo.pet.enums.TipoRotina;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtividadeDiariaResponseDTO {

    private Long id;
    private Long pacienteId;
    private String nomePaciente;
    private TipoRotina tipo;
    private String description;
    private LocalDate dataRegistro;
}
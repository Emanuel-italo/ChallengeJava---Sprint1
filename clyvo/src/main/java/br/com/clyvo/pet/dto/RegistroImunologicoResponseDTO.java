package br.com.clyvo.pet.dto;

import br.com.clyvo.pet.enums.StatusImunizacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroImunologicoResponseDTO {

    private Long id;
    private Long pacienteId;
    private String nomePaciente;
    private String nomeVacina;
    private String lote;
    private LocalDate dataAplicacao;
    private LocalDate dataVencimento;
    private StatusImunizacao status;
}
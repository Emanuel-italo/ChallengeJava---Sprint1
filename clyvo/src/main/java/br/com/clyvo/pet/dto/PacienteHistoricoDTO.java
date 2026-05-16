package br.com.clyvo.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteHistoricoDTO {

    private PacienteResponseDTO pet;
    private List<VaccineResponseDTO> vaccines;
    private List<RoutineResponseDTO> routines;
    private List<AlertaPreditivoResponseDTO> alerts;
}


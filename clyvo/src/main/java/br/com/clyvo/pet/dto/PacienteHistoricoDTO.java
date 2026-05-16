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

    private PacienteResponseDTO paciente;
    private List<AlertaPreditivoResponseDTO> alertas;


    private List<Object> imunizacoes;
    private List<Object> atividades;
}
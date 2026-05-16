package br.com.clyvo.pet.dto;

import br.com.clyvo.pet.enums.CategoriaEspecie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteResponseDTO {

    private Long idPaciente;
    private String apelido;
    private CategoriaEspecie species;
    private String breed;
    private LocalDate birthDate;
    private Double weight;
    private String tutorName;
    private String tutorPhone;
    private Boolean active;
    private Integer ageInMonths;
}


package br.com.clyvo.pet.dto;

import br.com.clyvo.pet.enums.RoutineType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoutineResponseDTO {

    private Long id;
    private Long petId;
    private String petName;
    private RoutineType type;
    private String description;
    private LocalDate recordDate;
}


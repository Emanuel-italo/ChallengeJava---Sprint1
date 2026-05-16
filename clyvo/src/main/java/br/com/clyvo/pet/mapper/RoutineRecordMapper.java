package br.com.clyvo.pet.mapper;

import br.com.clyvo.pet.dto.RoutineRequestDTO;
import br.com.clyvo.pet.dto.RoutineResponseDTO;
import br.com.clyvo.pet.entity.PacienteAnimal;
import br.com.clyvo.pet.entity.AtividadeDiaria;
import org.springframework.stereotype.Component;

@Component
public class RoutineRecordMapper {

    public AtividadeDiaria toEntity(RoutineRequestDTO dto, PacienteAnimal pet) {
        return AtividadeDiaria.builder()
                .pet(pet)
                .type(dto.getType())
                .description(dto.getDescription())
                .recordDate(dto.getRecordDate())
                .build();
    }

    public RoutineResponseDTO toResponseDTO(AtividadeDiaria record) {
        return RoutineResponseDTO.builder()
                .id(record.getId())
                .petId(record.getPet().getId())
                .petName(record.getPet().getName())
                .type(record.getType())
                .description(record.getDescription())
                .recordDate(record.getRecordDate())
                .build();
    }

    public void updateEntity(AtividadeDiaria record, RoutineRequestDTO dto) {
        record.setType(dto.getType());
        record.setDescription(dto.getDescription());
        record.setRecordDate(dto.getRecordDate());
    }
}


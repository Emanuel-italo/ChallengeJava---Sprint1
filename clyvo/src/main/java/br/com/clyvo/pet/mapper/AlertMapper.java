package br.com.clyvo.pet.mapper;

import br.com.clyvo.pet.dto.AlertaPreditivoRequestDTO;
import br.com.clyvo.pet.dto.AlertaPreditivoResponseDTO;
import br.com.clyvo.pet.entity.AlertaPreditivo;
import br.com.clyvo.pet.entity.Pet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AlertMapper {

    public AlertaPreditivo toEntity(AlertaPreditivoRequestDTO dto, Pet pet) {
        return AlertaPreditivo.builder()
                .pet(pet)
                .type(dto.getType())
                .message(dto.getMessage())
                .dueDate(dto.getDueDate())
                .sent(false)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public AlertaPreditivoResponseDTO toResponseDTO(AlertaPreditivo alert) {
        return AlertaPreditivoResponseDTO.builder()
                .id(alert.getId())
                .petId(alert.getPet().getId())
                .petName(alert.getPet().getName())
                .type(alert.getType())
                .message(alert.getMessage())
                .dueDate(alert.getDueDate())
                .sent(alert.getSent())
                .createdAt(alert.getCreatedAt())
                .build();
    }

    public void updateEntity(AlertaPreditivo alert, AlertaPreditivoRequestDTO dto) {
        alert.setType(dto.getType());
        alert.setMessage(dto.getMessage());
        alert.setDueDate(dto.getDueDate());
    }
}


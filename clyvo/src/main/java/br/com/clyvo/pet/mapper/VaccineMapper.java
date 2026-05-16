package br.com.clyvo.pet.mapper;

import br.com.clyvo.pet.dto.VaccineRequestDTO;
import br.com.clyvo.pet.dto.VaccineResponseDTO;
import br.com.clyvo.pet.entity.PacienteAnimal;
import br.com.clyvo.pet.entity.RegistroImunologico;
import br.com.clyvo.pet.enums.VaccineStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class VaccineMapper {

    public RegistroImunologico toEntity(VaccineRequestDTO dto, PacienteAnimal pet) {
        VaccineStatus status = dto.getStatus() != null ? dto.getStatus() : VaccineStatus.PENDING;
        return RegistroImunologico.builder()
                .pet(pet)
                .name(dto.getName())
                .applicationDate(dto.getApplicationDate())
                .dueDate(dto.getDueDate())
                .status(status)
                .build();
    }

    public VaccineResponseDTO toResponseDTO(RegistroImunologico vaccine) {
        boolean expiringSoon = false;
        if (vaccine.getDueDate() != null && vaccine.getStatus() != VaccineStatus.APPLIED) {
            expiringSoon = vaccine.getDueDate().isBefore(LocalDate.now().plusDays(30));
        }
        return VaccineResponseDTO.builder()
                .id(vaccine.getId())
                .petId(vaccine.getPet().getId())
                .petName(vaccine.getPet().getName())
                .name(vaccine.getName())
                .applicationDate(vaccine.getApplicationDate())
                .dueDate(vaccine.getDueDate())
                .status(vaccine.getStatus())
                .expiringSoon(expiringSoon)
                .build();
    }

    public void updateEntity(RegistroImunologico vaccine, VaccineRequestDTO dto) {
        vaccine.setName(dto.getName());
        vaccine.setApplicationDate(dto.getApplicationDate());
        vaccine.setDueDate(dto.getDueDate());
        if (dto.getStatus() != null) {
            vaccine.setStatus(dto.getStatus());
        }
    }
}


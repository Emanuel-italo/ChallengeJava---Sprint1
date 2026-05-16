package br.com.clyvo.pet.mapper;

import br.com.clyvo.pet.dto.PacienteRequestDTO;
import br.com.clyvo.pet.dto.PacienteResponseDTO;
import br.com.clyvo.pet.entity.PacienteAnimal;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class PetMapper {

    public PacienteAnimal toEntity(PacienteRequestDTO dto) {
        return PacienteAnimal.builder()
                .name(dto.getName())
                .species(dto.getSpecies())
                .breed(dto.getBreed())
                .birthDate(dto.getBirthDate())
                .weight(dto.getWeight())
                .tutorName(dto.getTutorName())
                .tutorPhone(dto.getTutorPhone())
                .active(true)
                .build();
    }

    public PacienteResponseDTO toResponseDTO(PacienteAnimal pet) {
        Integer ageInMonths = null;
        if (pet.getBirthDate() != null) {
            Period period = Period.between(pet.getBirthDate(), LocalDate.now());
            ageInMonths = period.getYears() * 12 + period.getMonths();
        }
        return PacienteResponseDTO.builder()
                .id(pet.getId())
                .name(pet.getName())
                .species(pet.getSpecies())
                .breed(pet.getBreed())
                .birthDate(pet.getBirthDate())
                .weight(pet.getWeight())
                .tutorName(pet.getTutorName())
                .tutorPhone(pet.getTutorPhone())
                .active(pet.getActive())
                .ageInMonths(ageInMonths)
                .build();
    }

    public void updateEntity(PacienteAnimal pet, PacienteRequestDTO dto) {
        pet.setName(dto.getName());
        pet.setSpecies(dto.getSpecies());
        pet.setBreed(dto.getBreed());
        pet.setBirthDate(dto.getBirthDate());
        pet.setWeight(dto.getWeight());
        pet.setTutorName(dto.getTutorName());
        pet.setTutorPhone(dto.getTutorPhone());
    }
}


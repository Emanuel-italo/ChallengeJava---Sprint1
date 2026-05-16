package br.com.clyvo.pet.service;

import br.com.clyvo.pet.dto.PetHistoryDTO;
import br.com.clyvo.pet.dto.PetRequestDTO;
import br.com.clyvo.pet.dto.PetResponseDTO;
import br.com.clyvo.pet.entity.PacienteAnimal;
import br.com.clyvo.pet.enums.CategoriaEspecie;
import br.com.clyvo.pet.exception.ResourceNotFoundException;
import br.com.clyvo.pet.mapper.AlertMapper;
import br.com.clyvo.pet.mapper.PetMapper;
import br.com.clyvo.pet.mapper.RoutineRecordMapper;
import br.com.clyvo.pet.mapper.VaccineMapper;
import br.com.clyvo.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;
    private final VaccineMapper vaccineMapper;
    private final RoutineRecordMapper routineRecordMapper;
    private final AlertMapper alertMapper;

    @Cacheable("pets")
    @Transactional(readOnly = true)
    public Page<PetResponseDTO> findAll(Pageable pageable) {
        return petRepository.findByActiveTrue(pageable)
                .map(petMapper::toResponseDTO);
    }

    @Cacheable(value = "pet", key = "#id")
    @Transactional(readOnly = true)
    public PetResponseDTO findById(Long id) {
        return petMapper.toResponseDTO(findPetById(id));
    }

    @Transactional(readOnly = true)
    public Page<PetResponseDTO> searchByName(String name, Pageable pageable) {
        return petRepository.findByNameContainingIgnoreCaseAndActiveTrue(name, pageable)
                .map(petMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public Page<PetResponseDTO> findBySpecies(CategoriaEspecie species, Pageable pageable) {
        return petRepository.findBySpeciesAndActiveTrue(species, pageable)
                .map(petMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public List<PetResponseDTO> findPetsWithVaccinesDueOrExpiringSoon() {
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysFromNow = today.plusDays(30);
        return petRepository.findPetsWithVaccinesDueOrExpiringSoon(today, thirtyDaysFromNow)
                .stream()
                .map(petMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public PetHistoryDTO getPetHistory(Long id) {
        PacienteAnimal pet = findPetById(id);
        return PetHistoryDTO.builder()
                .pet(petMapper.toResponseDTO(pet))
                .vaccines(pet.getVaccines().stream().map(vaccineMapper::toResponseDTO).toList())
                .routines(pet.getRoutineRecords().stream().map(routineRecordMapper::toResponseDTO).toList())
                .alerts(pet.getAlerts().stream().map(alertMapper::toResponseDTO).toList())
                .build();
    }

    @CacheEvict(value = {"pets", "pet"}, allEntries = true)
    @Transactional
    public PetResponseDTO create(PetRequestDTO dto) {
        PacienteAnimal pet = petMapper.toEntity(dto);
        return petMapper.toResponseDTO(petRepository.save(pet));
    }

    @CacheEvict(value = {"pets", "pet"}, allEntries = true)
    @Transactional
    public PetResponseDTO update(Long id, PetRequestDTO dto) {
        PacienteAnimal pet = findPetById(id);
        petMapper.updateEntity(pet, dto);
        return petMapper.toResponseDTO(petRepository.save(pet));
    }

    @CacheEvict(value = {"pets", "pet"}, allEntries = true)
    @Transactional
    public void delete(Long id) {
        PacienteAnimal pet = findPetById(id);
        pet.setActive(false);
        petRepository.save(pet);
    }

    public PacienteAnimal findPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet", id));
    }
}


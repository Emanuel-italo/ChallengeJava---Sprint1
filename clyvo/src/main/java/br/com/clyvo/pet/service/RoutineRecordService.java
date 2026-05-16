package br.com.clyvo.pet.service;

import br.com.clyvo.pet.dto.RoutineRequestDTO;
import br.com.clyvo.pet.dto.RoutineResponseDTO;
import br.com.clyvo.pet.entity.PacienteAnimal;
import br.com.clyvo.pet.entity.AtividadeDiaria;
import br.com.clyvo.pet.exception.ResourceNotFoundException;
import br.com.clyvo.pet.mapper.RoutineRecordMapper;
import br.com.clyvo.pet.repository.RoutineRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutineRecordService {

    private final RoutineRecordRepository routineRecordRepository;
    private final PacienteAnimalService petService;
    private final RoutineRecordMapper routineRecordMapper;

    @Transactional(readOnly = true)
    public Page<RoutineResponseDTO> findAll(Pageable pageable) {
        return routineRecordRepository.findAll(pageable).map(routineRecordMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public RoutineResponseDTO findById(Long id) {
        return routineRecordMapper.toResponseDTO(findRecordById(id));
    }

    @Transactional(readOnly = true)
    public List<RoutineResponseDTO> findByPetId(Long petId) {
        petService.findPetById(petId);
        return routineRecordRepository.findByPetIdOrderByRecordDateDesc(petId)
                .stream().map(routineRecordMapper::toResponseDTO).toList();
    }

    @Transactional
    public RoutineResponseDTO create(RoutineRequestDTO dto) {
        PacienteAnimal pet = petService.findPetById(dto.getPetId());
        AtividadeDiaria record = routineRecordMapper.toEntity(dto, pet);
        return routineRecordMapper.toResponseDTO(routineRecordRepository.save(record));
    }

    @Transactional
    public RoutineResponseDTO update(Long id, RoutineRequestDTO dto) {
        AtividadeDiaria record = findRecordById(id);
        routineRecordMapper.updateEntity(record, dto);
        return routineRecordMapper.toResponseDTO(routineRecordRepository.save(record));
    }

    @Transactional
    public void delete(Long id) {
        AtividadeDiaria record = findRecordById(id);
        routineRecordRepository.delete(record);
    }

    private AtividadeDiaria findRecordById(Long id) {
        return routineRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de rotina", id));
    }
}


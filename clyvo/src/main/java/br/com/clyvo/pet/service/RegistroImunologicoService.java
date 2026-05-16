package br.com.clyvo.pet.service;


import br.com.clyvo.pet.dto.RegistroImunologicoRequestDTO;
import br.com.clyvo.pet.dto.RegistroImunologicoResponseDTO;
import br.com.clyvo.pet.entity.PacienteAnimal;
import br.com.clyvo.pet.entity.RegistroImunologico;
import br.com.clyvo.pet.repository.RegistroImunologicoDao;
import br.com.clyvo.pet.repository.PacienteAnimalDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class RegistroImunologicoService {

    @Service
    @RequiredArgsConstructor
    public class RegistroImunologicoService {

        private final RegistroImunologicoDao registroDao;
        private final PacienteAnimalDao pacienteDao;

        public List<RegistroImunologicoResponseDTO> buscarTodos() {
            return registroDao.buscarTodos()
                    .stream()
                    .map(this::converterParaResponseDTO)
                    .collect(Collectors.toList());
        }

        public RegistroImunologicoResponseDTO buscarPorId(Long id) {
            RegistroImunologico registro = registroDao.buscarPorId(id);
            return converterParaResponseDTO(registro);
        }

        public List<RegistroImunologicoResponseDTO> buscarPorPacienteId(Long pacienteId) {
            pacienteDao.buscarPorId(pacienteId);
            return registroDao.buscarPorPaciente(pacienteId)
                    .stream()
                    .map(this::converterParaResponseDTO)
                    .collect(Collectors.toList());
        }



}

package br.com.clyvo.pet.service;



import br.com.clyvo.pet.dto.AtividadeDiariaRequestDTO;
import br.com.clyvo.pet.dto.AtividadeDiariaResponseDTO;
import br.com.clyvo.pet.entity.PacienteAnimal;
import br.com.clyvo.pet.entity.AtividadeDiaria;
import br.com.clyvo.pet.repository.AtividadeDiariaDao;
import br.com.clyvo.pet.repository.PacienteAnimalDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AtividadeDiariaService {

    private final AtividadeDiariaDao atividadeDao;
    private final PacienteAnimalDao pacienteDao;

    public List<AtividadeDiariaResponseDTO> buscarTodas() {
        return atividadeDao.buscarTodos()
                .stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public AtividadeDiariaResponseDTO buscarPorId(Long id) {
        AtividadeDiaria atividade = atividadeDao.buscarPorId(id);
        return converterParaResponseDTO(atividade);
    }

    public List<AtividadeDiariaResponseDTO> buscarPorPacienteId(Long pacienteId) {
        pacienteDao.buscarPorId(pacienteId);
        return atividadeDao.buscarPorPaciente(pacienteId)
                .stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public AtividadeDiariaResponseDTO registrar(AtividadeDiariaRequestDTO dto) {
        PacienteAnimal paciente = pacienteDao.buscarPorId(dto.getPacienteId());

        AtividadeDiaria novaAtividade = AtividadeDiaria.builder()
                .paciente(paciente)
                .tipo(dto.getTipo())
                .descricao(dto.getDescricao())
                .dataRegistro(dto.getDataRegistro())
                .build();

        return converterParaResponseDTO(atividadeDao.salvar(novaAtividade));
    }

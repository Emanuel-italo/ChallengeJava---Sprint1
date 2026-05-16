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

    public RegistroImunologicoResponseDTO registrar(RegistroImunologicoRequestDTO dto) {
        PacienteAnimal paciente = pacienteDao.buscarPorId(dto.getPacienteId());

        RegistroImunologico novoRegistro = RegistroImunologico.builder()
                .paciente(paciente)
                .nomeVacina(dto.getNomeVacina())
                .lote(dto.getLote())
                .dataAplicacao(dto.getDataAplicacao())
                .dataVencimento(dto.getDataVencimento())
                .status(dto.getStatus())
                .build();

        return converterParaResponseDTO(registroDao.salvar(novoRegistro));
    }

    public RegistroImunologicoResponseDTO atualizar(Long id, RegistroImunologicoRequestDTO dto) {
        RegistroImunologico registro = registroDao.buscarPorId(id);

        registro.setNomeVacina(dto.getNomeVacina());
        registro.setLote(dto.getLote());
        registro.setDataAplicacao(dto.getDataAplicacao());
        registro.setDataVencimento(dto.getDataVencimento());
        registro.setStatus(dto.getStatus());

        return converterParaResponseDTO(registroDao.atualizar(registro));
    }
    public void remover(Long id) {
        registroDao.remover(id);
    }

    private RegistroImunologicoResponseDTO converterParaResponseDTO(RegistroImunologico registro) {
        return RegistroImunologicoResponseDTO.builder()
                .id(registro.getIdRegistro())
                .pacienteId(registro.getPaciente().getIdPaciente())
                .nomePaciente(registro.getPaciente().getApelido())
                .nomeVacina(registro.getNomeVacina())
                .lote(registro.getLote())
                .dataAplicacao(registro.getDataAplicacao())
                .dataVencimento(registro.getDataVencimento())
                .status(registro.getStatus())
                .build();
    }
}


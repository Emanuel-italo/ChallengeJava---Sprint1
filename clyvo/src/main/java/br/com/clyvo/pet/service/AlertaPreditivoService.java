package br.com.clyvo.pet.service;

import br.com.clyvo.pet.dto.AlertaPreditivoRequestDTO;
import br.com.clyvo.pet.dto.AlertaPreditivoResponseDTO;
import br.com.clyvo.pet.entity.AlertaPreditivo;
import br.com.clyvo.pet.entity.PacienteAnimal;
import br.com.clyvo.pet.repository.AlertaPreditivoDao;
import br.com.clyvo.pet.repository.PacienteAnimalDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.com.clyvo.pet.exception.EntidadeNaoLocalizadaException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlertaPreditivoService {

    // Utilizando as classes DAO manuais (JPA raiz) ao invés do Spring Data
    private final AlertaPreditivoDao alertaDao;
    private final PacienteAnimalDao pacienteDao;

    public List<AlertaPreditivoResponseDTO> buscarTodos() {
        return alertaDao.buscarTodos()
                .stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public AlertaPreditivoResponseDTO buscarPorId(Long id) {
        AlertaPreditivo alerta = alertaDao.buscarPorId(id);
        return converterParaResponseDTO(alerta);
    }

    public List<AlertaPreditivoResponseDTO> buscarPorPacienteId(Long pacienteId) {
        // Valida se o paciente existe antes de buscar os alertas
        pacienteDao.buscarPorId(pacienteId);
        return alertaDao.buscarPorPaciente(pacienteId)
                .stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public List<AlertaPreditivoResponseDTO> buscarPendentesPorPacienteId(Long pacienteId) {
        pacienteDao.buscarPorId(pacienteId);
        return alertaDao.buscarPendentesPorPaciente(pacienteId)
                .stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public List<AlertaPreditivoResponseDTO> buscarTodosPendentes() {
        return alertaDao.buscarPendentes()
                .stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public AlertaPreditivoResponseDTO registrar(AlertaPreditivoRequestDTO dto) {
        PacienteAnimal paciente = pacienteDao.buscarPorId(dto.getPacienteId());

        AlertaPreditivo novoAlerta = new AlertaPreditivo();
        novoAlerta.setPaciente(paciente);
        novoAlerta.setTipo(dto.getTipo());
        novoAlerta.setMensagem(dto.getMensagem());
        novoAlerta.setDataPrevista(dto.getDataPrevista());
        novoAlerta.setEnviado(false);
        novoAlerta.setDataCriacao(LocalDateTime.now());

        AlertaPreditivo alertaSalvo = alertaDao.salvar(novoAlerta);
        return converterParaResponseDTO(alertaSalvo);
    }

    public AlertaPreditivoResponseDTO atualizar(Long id, AlertaPreditivoRequestDTO dto) {
        AlertaPreditivo alerta = alertaDao.buscarPorId(id);

        alerta.setTipo(dto.getTipo());
        alerta.setMensagem(dto.getMensagem());
        alerta.setDataPrevista(dto.getDataPrevista());
        // Se o paciente também puder ser trocado, faríamos a busca novamente aqui

        AlertaPreditivo alertaAtualizado = alertaDao.atualizar(alerta);
        return converterParaResponseDTO(alertaAtualizado);
    }

    public AlertaPreditivoResponseDTO marcarComoEnviado(Long id) {
        AlertaPreditivo alerta = alertaDao.buscarPorId(id);
        alerta.setEnviado(true);

        AlertaPreditivo alertaAtualizado = alertaDao.atualizar(alerta);
        return converterParaResponseDTO(alertaAtualizado);
    }

    public void remover(Long id) {
        alertaDao.remover(id);
    }

    // Método auxiliar privado para montar o DTO (substitui o antigo AlertMapper)
    private AlertaPreditivoResponseDTO converterParaResponseDTO(AlertaPreditivo alerta) {
        return AlertaPreditivoResponseDTO.builder()
                .id(alerta.getIdRegistro())
                .pacienteId(alerta.getPaciente().getIdPaciente())
                .nomePaciente(alerta.getPaciente().getApelido())
                .tipo(alerta.getTipo())
                .mensagem(alerta.getMensagem())
                .dataPrevista(alerta.getDataPrevista())
                .enviado(alerta.getEnviado())
                .dataCriacao(alerta.getDataCriacao())
                .build();
    }
}
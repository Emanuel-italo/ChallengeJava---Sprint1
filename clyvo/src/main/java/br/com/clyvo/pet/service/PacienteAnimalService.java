package br.com.clyvo.pet.service;

import br.com.clyvo.pet.dto.PacienteHistoricoDTO;
import br.com.clyvo.pet.dto.PacienteRequestDTO;
import br.com.clyvo.pet.dto.PacienteResponseDTO;
import br.com.clyvo.pet.dto.AlertaPreditivoResponseDTO;
import br.com.clyvo.pet.entity.PacienteAnimal;
import br.com.clyvo.pet.enums.CategoriaEspecie;
import br.com.clyvo.pet.core.exception.EntidadeNaoLocalizadaException;
import br.com.clyvo.pet.repository.PacienteAnimalDao;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteAnimalService {

    private final PacienteAnimalDao pacienteDao;

    @Cacheable("pacientes_ativos")
    public List<PacienteResponseDTO> buscarTodosAtivos() {
        return pacienteDao.buscarTodosAtivos()
                .stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "paciente_especifico", key = "#id")
    public PacienteResponseDTO buscarPorId(Long id) {
        PacienteAnimal paciente = pacienteDao.buscarPorId(id);
        return converterParaResponseDTO(paciente);
    }

    public List<PacienteResponseDTO> buscarPorNome(String nome) {
        return pacienteDao.buscarPorNomeContendo(nome)
                .stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public List<PacienteResponseDTO> buscarPorEspecie(CategoriaEspecie especie) {
        return pacienteDao.buscarPorEspecie(especie)
                .stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public List<PacienteResponseDTO> buscarPacientesComImunizacoesCriticas() {
        LocalDate hoje = LocalDate.now();
        LocalDate limiteTrintaDias = hoje.plusDays(30);
        return pacienteDao.buscarPacientesComImunizacoesAtrasadasOuVencendo(hoje, limiteTrintaDias)
                .stream()
                .map(this::converterParaResponseDTO)
                .collect(Collectors.toList());
    }

    public PacienteHistoricoDTO obtenerHistoricoLongitudinal(Long id) {
        PacienteAnimal paciente = pacienteDao.buscarPorId(id);

        // Converte sub-listas de históricos clínicos de forma segura
        List<AlertaPreditivoResponseDTO> alertasDTO = paciente.getAlertasPreditivos().stream()
                .map(alerta -> AlertaPreditivoResponseDTO.builder()
                        .id(alerta.getIdRegistro())
                        .pacienteId(paciente.getIdPaciente())
                        .nomePaciente(paciente.getApelido())
                        .tipo(alerta.getTipo())
                        .mensagem(alerta.getMensagem())
                        .dataPrevista(alerta.getDataPrevista())
                        .enviado(alerta.getEnviado())
                        .dataCriacao(alerta.getDataCriacao())
                        .build())
                .collect(Collectors.toList());

        return PacienteHistoricoDTO.builder()
                .paciente(converterParaResponseDTO(paciente))
                .alertas(alertasDTO)
                .imunizacoes(new ArrayList<>()) // Será mapeado quando criarmos a DTO de RegistroImunologico
                .atividades(new ArrayList<>())   // Será mapeado quando criarmos a DTO de AtividadeDiaria
                .build();
    }

    @CacheEvict(value = {"pacientes_ativos", "paciente_especifico"}, allEntries = true)
    public PacienteResponseDTO criar(PacienteRequestDTO dto) {
        PacienteAnimal novoPaciente = PacienteAnimal.builder()
                .apelido(dto.getApelido())
                .especie(dto.getEspecie())
                .raca(dto.getRaca())
                .dataNascimento(dto.getDataNascimento())
                .peso(dto.getPeso())
                .responsavelLegal(dto.getResponsavelLegal())
                .telefoneContato(dto.getTelefoneContato())
                .prontuarioDetalhado(dto.getProntuarioDetalhado())
                .ativo(true)
                .build();

        PacienteAnimal salvo = pacienteDao.salvar(novoPaciente);
        return converterParaResponseDTO(salvo);
    }

    @CacheEvict(value = {"pacientes_ativos", "paciente_especifico"}, allEntries = true)
    public PacienteResponseDTO atualizar(Long id, PacienteRequestDTO dto) {
        PacienteAnimal pacienteExistente = pacienteDao.buscarPorId(id);

        pacienteExistente.setApelido(dto.getApelido());
        pacienteExistente.setEspecie(dto.getEspecie());
        pacienteExistente.setRaca(dto.getRaca());
        pacienteExistente.setDataNascimento(dto.getDataNascimento());
        pacienteExistente.setPeso(dto.getPeso());
        pacienteExistente.setResponsavelLegal(dto.getResponsavelLegal());
        pacienteExistente.setTelefoneContato(dto.getTelefoneContato());
        pacienteExistente.setProntuarioDetalhado(dto.getProntuarioDetalhado());

        PacienteAnimal atualizado = pacienteDao.atualizar(pacienteExistente);
        return converterParaResponseDTO(atualizado);
    }

    @CacheEvict(value = {"pacientes_ativos", "paciente_especifico"}, allEntries = true)
    public void inativar(Long id) {
        PacienteAnimal paciente = pacienteDao.buscarPorId(id);
        paciente.setAtivo(false);
        pacienteDao.atualizar(paciente); // Soft delete via atualização de estado
    }

    // Método privado auxiliar para realizar a conversão de dados e calcular idade em meses
    private PacienteResponseDTO converterParaResponseDTO(PacienteAnimal paciente) {
        Integer idadeEmMeses = null;
        if (paciente.getDataNascimento() != null) {
            Period periodo = Period.between(paciente.getDataNascimento(), LocalDate.now());
            idadeEmMeses = (periodo.getYears() * 12) + periodo.getMonths();
        }

        return PacienteResponseDTO.builder()
                .idPaciente(paciente.getIdPaciente())
                .apelido(paciente.getApelido())
                .especie(paciente.getEspecie())
                .raca(paciente.getRaca())
                .dataNascimento(paciente.getDataNascimento())
                .peso(paciente.getPeso())
                .responsavelLegal(paciente.getResponsavelLegal())
                .telefoneContato(paciente.getTelefoneContato())
                .prontuarioDetalhado(paciente.getProntuarioDetalhado())
                .ativo(paciente.getAtivo())
                .idadeEmMeses(idadeEmMeses)
                .build();
    }
}
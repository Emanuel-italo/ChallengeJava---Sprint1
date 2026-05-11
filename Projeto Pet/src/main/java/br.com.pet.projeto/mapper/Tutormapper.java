package br.com.fiap.clyvovet.mapper;

import br.com.fiap.clyvovet.dto.request.TutorRequestDTO;
import br.com.fiap.clyvovet.dto.response.TutorResponseDTO;
import br.com.fiap.clyvovet.entity.Tutor;
import org.springframework.stereotype.Component;


@Component
public class TutorMapper {

    public Tutor toEntity(TutorRequestDTO dto) {
        return Tutor.builder()
                .nome(dto.nome())
                .cpf(dto.cpf())
                .email(dto.email())
                .telefone(dto.telefone())
                .dataNascimento(dto.dataNascimento())
                .aceitaComunicacao(dto.aceitaComunicacao() == null ? Boolean.TRUE : dto.aceitaComunicacao())
                .build();
    }

    public void atualizarEntity(Tutor tutor, TutorRequestDTO dto) {
        tutor.setNome(dto.nome());
        tutor.setCpf(dto.cpf());
        tutor.setEmail(dto.email());
        tutor.setTelefone(dto.telefone());
        tutor.setDataNascimento(dto.dataNascimento());
        if (dto.aceitaComunicacao() != null) {
            tutor.setAceitaComunicacao(dto.aceitaComunicacao());
        }
    }

    public TutorResponseDTO toResponse(Tutor t) {
        return new TutorResponseDTO(
                t.getId(),
                t.getNome(),
                t.getCpf(),
                t.getEmail(),
                t.getTelefone(),
                t.getDataNascimento(),
                t.getAceitaComunicacao(),
                t.getPets() == null ? 0 : t.getPets().size(),
                t.getCriadoEm(),
                t.getAtualizadoEm()
        );
    }
}
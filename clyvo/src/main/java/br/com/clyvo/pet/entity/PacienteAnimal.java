package br.com.clyvo.pet.entity;

import br.com.clyvo.pet.core.annotations.ChavePrimaria;
import br.com.clyvo.pet.core.annotations.ColunaMapeada;
import br.com.clyvo.pet.core.annotations.TabelaMapeada;
import br.com.clyvo.pet.enums.CategoriaEspecie;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_PACIENTE_ANIMAL")
@TabelaMapeada(nome = "TB_PACIENTE_ANIMAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_paciente")
    @SequenceGenerator(name = "seq_paciente", sequenceName = "SEQ_PACIENTE_ANIMAL", allocationSize = 1)
    @ChavePrimaria
    @ColunaMapeada(nome = "ID_PACIENTE")
    @Column(name = "ID_PACIENTE")
    private Long idPaciente;

    @Column(name = "NOME_ANIMAL", nullable = false, length = 100)
    @ColunaMapeada(nome = "NOME_ANIMAL")
    private String apelido;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORIA_ESPECIE", nullable = false)
    @ColunaMapeada(nome = "CATEGORIA_ESPECIE")
    private CategoriaEspecie especie;

    @Column(name = "RACA_ANIMAL", length = 100)
    @ColunaMapeada(nome = "RACA_ANIMAL")
    private String raca;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_NASCIMENTO")
    @ColunaMapeada(nome = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "PESO_ATUAL")
    @ColunaMapeada(nome = "PESO_ATUAL")
    private Double peso;

    @Column(name = "RESPONSAVEL_LEGAL", nullable = false, length = 100)
    @ColunaMapeada(nome = "RESPONSAVEL_LEGAL")
    private String responsavelLegal;

    @Column(name = "TELEFONE_CONTATO", length = 20)
    @ColunaMapeada(nome = "TELEFONE_CONTATO")
    private String telefoneContato;

    @Lob
    @Column(name = "PRONTUARIO_DETALHADO")
    @ColunaMapeada(nome = "PRONTUARIO_DETALHADO")
    private String prontuarioDetalhado;

    @Column(name = "STATUS_ATIVO", nullable = false)
    @ColunaMapeada(nome = "STATUS_ATIVO")
    @Builder.Default
    private Boolean ativo = true;

    @Column(name = "ULTIMA_ATUALIZACAO")
    @ColunaMapeada(nome = "ULTIMA_ATUALIZACAO")
    private LocalDateTime ultimaAtualizacao;

    // Relacionamentos ajustados para os novos domínios
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RegistroImunologico> imunizacoes = new ArrayList<>();

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AtividadeDiaria> atividadesDiarias = new ArrayList<>();

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AlertaPreditivo> alertasPreditivos = new ArrayList<>();

    // Callback JPA exigido nos requisitos
    @PreUpdate
    public void antesDeAtualizar() {
        this.ultimaAtualizacao = LocalDateTime.now();
    }
}
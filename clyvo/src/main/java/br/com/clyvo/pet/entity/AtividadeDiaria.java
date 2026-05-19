package br.com.clyvo.pet.entity;

import br.com.clyvo.pet.core.annotations.ChavePrimaria;
import br.com.clyvo.pet.core.annotations.ColunaMapeada;
import br.com.clyvo.pet.core.annotations.TabelaMapeada;
import br.com.clyvo.pet.enums.TipoRotina;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "TB_ATIVIDADE_DIARIA")
@TabelaMapeada(nome = "TB_ATIVIDADE_DIARIA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtividadeDiaria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_atividade")
    @SequenceGenerator(name = "seq_atividade", sequenceName = "SEQ_ATIVIDADE_DIARIA", allocationSize = 1, initialValue = 10)
    @ChavePrimaria
    @ColunaMapeada(nome = "ID_ATIVIDADE")
    @Column(name = "ID_ATIVIDADE")
    private Long idRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PACIENTE", nullable = false)
    @ColunaMapeada(nome = "ID_PACIENTE")
    private PacienteAnimal paciente;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_ATIVIDADE", nullable = false)
    @ColunaMapeada(nome = "TIPO_ATIVIDADE")
    private TipoRotina tipo;

    @Column(name = "DESCRICAO_ATIVIDADE", length = 500)
    @ColunaMapeada(nome = "DESCRICAO_ATIVIDADE")
    private String descricao;

    @Column(name = "DATA_REGISTRO", nullable = false)
    @ColunaMapeada(nome = "DATA_REGISTRO")
    private LocalDate dataRegistro;
}
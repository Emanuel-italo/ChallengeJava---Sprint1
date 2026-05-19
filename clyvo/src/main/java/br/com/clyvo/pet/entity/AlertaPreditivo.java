package br.com.clyvo.pet.entity;

import br.com.clyvo.pet.core.annotations.ChavePrimaria;
import br.com.clyvo.pet.core.annotations.ColunaMapeada;
import br.com.clyvo.pet.core.annotations.TabelaMapeada;
import br.com.clyvo.pet.enums.TipoAlertaPreditivo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_ALERTA_PREDITIVO")
@TabelaMapeada(nome = "TB_ALERTA_PREDITIVO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertaPreditivo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_alerta")

    @SequenceGenerator(name = "seq_alerta", sequenceName = "SEQ_ALERTA_PREDITIVO", allocationSize = 1, initialValue = 10)
    @ChavePrimaria
    @ColunaMapeada(nome = "ID_ALERTA")
    @Column(name = "ID_ALERTA")
    private Long idRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PACIENTE", nullable = false)
    @ColunaMapeada(nome = "ID_PACIENTE")
    private PacienteAnimal paciente;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_ALERTA", nullable = false)
    @ColunaMapeada(nome = "TIPO_ALERTA")
    private TipoAlertaPreditivo tipo;

    @Column(name = "MENSAGEM_DESCRITIVA", nullable = false, length = 500)
    @ColunaMapeada(nome = "MENSAGEM_DESCRITIVA")
    private String mensagem;

    @Column(name = "DATA_PREVISTA")
    @ColunaMapeada(nome = "DATA_PREVISTA")
    private LocalDate dataPrevista;

    @Column(name = "STATUS_ENVIADO", nullable = false)
    @ColunaMapeada(nome = "STATUS_ENVIADO")
    @Builder.Default
    private Boolean enviado = false;

    @Column(name = "CRIADO_EM", nullable = false, updatable = false)
    @ColunaMapeada(nome = "CRIADO_EM")
    private LocalDateTime dataCriacao;

    @PrePersist
    public void antesDeSalvar() {
        if (this.dataCriacao == null) {
            this.dataCriacao = LocalDateTime.now();
        }
    }
}
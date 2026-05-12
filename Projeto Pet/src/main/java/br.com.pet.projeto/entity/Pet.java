package br.com.fiap.clyvovet.entity;

import br.com.fiap.clyvovet.annotation.FatorRisco;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_pet")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @FatorRisco(peso = 5, descricao = "Doença crônica ativa eleva o risco")
    private Boolean possuiDoencaCronica;

    @FatorRisco(peso = 3, descricao = "Idade avançada é fator de atenção")
    private Integer idade; // Calculado transientement
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
}
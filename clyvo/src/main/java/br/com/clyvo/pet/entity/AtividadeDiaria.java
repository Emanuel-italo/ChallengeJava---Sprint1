package br.com.clyvo.pet.entity;

import br.com.clyvo.pet.enums.RoutineType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "routine_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtividadeDiaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private PacienteAnimal pet;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoutineType type;

    @Column(length = 500)
    private String description;

    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;
}


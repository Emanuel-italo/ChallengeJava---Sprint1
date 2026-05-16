import br.com.clyvo.pet.core.annotations.ChavePrimaria;
import br.com.clyvo.pet.core.annotations.ColunaMapeada;
import br.com.clyvo.pet.core.annotations.TabelaMapeada;
import br.com.clyvo.pet.enums.StatusImunizacao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "TB_REGISTRO_IMUNOLOGICO")
@TabelaMapeada(nome = "TB_REGISTRO_IMUNOLOGICO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistroImunologico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private PacienteAnimal pet;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "application_date")
    private LocalDate applicationDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private VaccineStatus status = VaccineStatus.PENDING;
}


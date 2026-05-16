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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_imunizacao")
    @SequenceGenerator(name = "seq_imunizacao", sequenceName = "SEQ_REGISTRO_IMUNOLOGICO", allocationSize = 1)
    @ChavePrimaria
    @ColunaMapeada(nome = "ID_IMUNIZACAO")
    @Column(name = "ID_IMUNIZACAO")
    private Long idRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PACIENTE", nullable = false)
    @ColunaMapeada(nome = "ID_PACIENTE")
    private PacienteAnimal paciente;

    @Column(name = "NOME_VACINA", nullable = false, length = 100)
    @ColunaMapeada(nome = "NOME_VACINA")
    private String nomeVacina;

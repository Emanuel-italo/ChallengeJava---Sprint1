package br.com.fiap.clyvovet.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "tb_tutor")
@Getter @Setter @NoArgsConstructor
public class Tutor extends Pessoa {
    
    private Boolean aceitaComunicacao;
    
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<Pet> pets;
}
package br.com.clyvo.pet.repository;

import br.com.clyvo.pet.entity.AtividadeDiaria;
import br.com.clyvo.pet.enums.RoutineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRecordRepository extends JpaRepository<AtividadeDiaria, Long> {

    List<AtividadeDiaria> findByPetIdOrderByRecordDateDesc(Long petId);

    List<AtividadeDiaria> findByPetIdAndType(Long petId, RoutineType type);
}


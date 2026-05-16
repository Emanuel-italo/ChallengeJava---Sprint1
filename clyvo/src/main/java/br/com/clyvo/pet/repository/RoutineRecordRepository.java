package br.com.clyvo.pet.repository;

import br.com.clyvo.pet.entity.RoutineRecord;
import br.com.clyvo.pet.enums.RoutineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRecordRepository extends JpaRepository<RoutineRecord, Long> {

    List<RoutineRecord> findByPetIdOrderByRecordDateDesc(Long petId);

    List<RoutineRecord> findByPetIdAndType(Long petId, RoutineType type);
}


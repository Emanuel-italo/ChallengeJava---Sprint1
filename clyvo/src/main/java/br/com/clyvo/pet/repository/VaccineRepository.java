package br.com.clyvo.pet.repository;

import br.com.clyvo.pet.entity.RegistroImunologico;
import br.com.clyvo.pet.enums.VaccineStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<RegistroImunologico, Long> {

    List<RegistroImunologico> findByPetId(Long petId);

    List<RegistroImunologico> findByPetIdAndStatus(Long petId, VaccineStatus status);

    @Query("SELECT v FROM Vaccine v WHERE v.pet.id = :petId AND (v.status = 'PENDING' OR v.status = 'EXPIRING_SOON')")
    List<RegistroImunologico> findPendingByPetId(@Param("petId") Long petId);

    @Query("SELECT v FROM Vaccine v WHERE v.dueDate <= :threshold AND v.status != 'APPLIED'")
    List<RegistroImunologico> findVaccinesDueBefore(@Param("threshold") LocalDate threshold);
}


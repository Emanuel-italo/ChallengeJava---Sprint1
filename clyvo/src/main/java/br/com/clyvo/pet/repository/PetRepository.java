package br.com.clyvo.pet.repository;

import br.com.clyvo.pet.entity.PacienteAnimal;
import br.com.clyvo.pet.enums.CategoriaEspecie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PacienteAnimal, Long> {

    Page<PacienteAnimal> findByActiveTrue(Pageable pageable);

    Page<PacienteAnimal> findByNameContainingIgnoreCaseAndActiveTrue(String name, Pageable pageable);

    Page<PacienteAnimal> findBySpeciesAndActiveTrue(CategoriaEspecie species, Pageable pageable);

    @Query("SELECT DISTINCT p FROM Pet p JOIN p.vaccines v " +
           "WHERE (v.dueDate <= :today OR v.dueDate <= :thirtyDaysFromNow) " +
           "AND p.active = true")
    List<PacienteAnimal> findPetsWithVaccinesDueOrExpiringSoon(
            @Param("today") LocalDate today,
            @Param("thirtyDaysFromNow") LocalDate thirtyDaysFromNow);
}


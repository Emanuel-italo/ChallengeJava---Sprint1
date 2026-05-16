package br.com.clyvo.pet.repository;

import br.com.clyvo.pet.entity.AlertaPreditivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<AlertaPreditivo, Long> {

    List<AlertaPreditivo> findByPetIdOrderByCreatedAtDesc(Long petId);

    List<AlertaPreditivo> findByPetIdAndSentFalseOrderByDueDateAsc(Long petId);

    List<AlertaPreditivo> findBySentFalseOrderByDueDateAsc();
}


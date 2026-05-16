package br.com.clyvo.project.repository;

import br.com.clyvo.project.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByPetIdOrderByCreatedAtDesc(Long petId);

    List<Alert> findByPetIdAndSentFalseOrderByDueDateAsc(Long petId);

    List<Alert> findBySentFalseOrderByDueDateAsc();
}


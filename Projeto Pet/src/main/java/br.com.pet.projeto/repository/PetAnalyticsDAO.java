package br.com.fiap.clyvovet.dao;

import br.com.fiap.clyvovet.entity.Pet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetAnalyticsDAO {

    @PersistenceContext
    private EntityManager entityManager;

    // Retorna pets em risco: Doença crônica + Consultas atrasadas
    public List<Pet> buscarPetsEmRiscoAbandonoClinico() {
        String jpql = "SELECT DISTINCT p FROM Pet p " +
                      "JOIN p.tutor t " +
                      "WHERE p.possuiDoencaCronica = true " +
                      "AND NOT EXISTS (SELECT c FROM Consulta c WHERE c.pet = p AND c.dataHora >= CURRENT_DATE - 180)";
        
        TypedQuery<Pet> query = entityManager.createQuery(jpql, Pet.class);
        return query.getResultList();
    }
}
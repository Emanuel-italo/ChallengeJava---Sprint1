package br.com.clyvo.pet.repository;

import br.com.clyvo.pet.entity.PacienteAnimal;
import br.com.clyvo.pet.enums.CategoriaEspecie;
import br.com.clyvo.pet.exception.EntidadeNaoLocalizadaException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class PacienteAnimalDao {

    @PersistenceContext
    private EntityManager entityManager;


    public PacienteAnimal salvar(PacienteAnimal paciente) {
        entityManager.persist(paciente);
        return paciente;
    }

    public PacienteAnimal atualizar(PacienteAnimal paciente) {
        return entityManager.merge(paciente);
    }

    @Transactional(readOnly = true)
    public PacienteAnimal buscarPorId(Long id) {
        PacienteAnimal paciente = entityManager.find(PacienteAnimal.class, id);
        if (paciente == null) {
            throw new EntidadeNaoLocalizadaException("Paciente Animal com o ID " + id + " não foi localizado no sistema.");
        }
        return paciente;
    }

    @Transactional(readOnly = true)
    public List<PacienteAnimal> buscarTodosAtivos() {
        String jpql = "SELECT p FROM PacienteAnimal p WHERE p.ativo = true";
        TypedQuery<PacienteAnimal> query = entityManager.createQuery(jpql, PacienteAnimal.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<PacienteAnimal> buscarPorNomeContendo(String nome) {
        String jpql = "SELECT p FROM PacienteAnimal p WHERE LOWER(p.apelido) LIKE LOWER(:nome) AND p.ativo = true";
        TypedQuery<PacienteAnimal> query = entityManager.createQuery(jpql, PacienteAnimal.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<PacienteAnimal> buscarPorEspecie(CategoriaEspecie especie) {
        String jpql = "SELECT p FROM PacienteAnimal p WHERE p.especie = :especie AND p.ativo = true";
        TypedQuery<PacienteAnimal> query = entityManager.createQuery(jpql, PacienteAnimal.class);
        query.setParameter("especie", especie);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<PacienteAnimal> buscarPacientesComImunizacoesAtrasadasOuVencendo(LocalDate dataInicio, LocalDate dataLimite) {
        // Esta JPQL faz um JOIN na lista de imunizações (antigas vacinas) para filtrar as pendências
        String jpql = "SELECT DISTINCT p FROM PacienteAnimal p " +
                "JOIN p.imunizacoes i " +
                "WHERE p.ativo = true " +
                "AND (i.dataVencimento < :dataInicio OR i.dataVencimento BETWEEN :dataInicio AND :dataLimite)";

        TypedQuery<PacienteAnimal> query = entityManager.createQuery(jpql, PacienteAnimal.class);
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataLimite", dataLimite);
        return query.getResultList();
    }
}
package br.com.clyvo.pet.repository;

import br.com.clyvo.pet.entity.PacienteAnimal;
import br.com.clyvo.pet.enums.CategoriaEspecie;
import br.com.clyvo.pet.core.exception.EntidadeNaoLocalizadaException;
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

    /**
     * Salva um novo paciente no banco de dados.
     */
    public PacienteAnimal salvar(PacienteAnimal paciente) {
        entityManager.persist(paciente);
        return paciente;
    }

    /**
     * Atualiza os dados de um paciente existente.
     */
    public PacienteAnimal atualizar(PacienteAnimal paciente) {
        return entityManager.merge(paciente);
    }

    /**
     * Busca um paciente pelo seu ID numérico.
     * Lança uma exceção customizada caso o registro não exista.
     */
    @Transactional(readOnly = true)
    public PacienteAnimal buscarPorId(Long id) {
        PacienteAnimal paciente = entityManager.find(PacienteAnimal.class, id);
        if (paciente == null) {
            throw new EntidadeNaoLocalizadaException("Paciente Animal com o ID " + id + " não foi localizado no sistema.");
        }
        return paciente;
    }

    /**
     * Lista todos os pacientes que estão com o status ativo (Soft Delete de segurança).
     */
    @Transactional(readOnly = true)
    public List<PacienteAnimal> buscarTodosAtivos() {
        String jpql = "SELECT p FROM PacienteAnimal p WHERE p.ativo = true";
        TypedQuery<PacienteAnimal> query = entityManager.createQuery(jpql, PacienteAnimal.class);
        return query.getResultList();
    }

    /**
     * Busca pacientes ativos cujo apelido/nome contenha o termo informado (Ignore Case).
     */
    @Transactional(readOnly = true)
    public List<PacienteAnimal> buscarPorNomeContendo(String nome) {
        String jpql = "SELECT p FROM PacienteAnimal p WHERE LOWER(p.apelido) LIKE LOWER(:nome) AND p.ativo = true";
        TypedQuery<PacienteAnimal> query = entityManager.createQuery(jpql, PacienteAnimal.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    /**
     * Filtra pacientes ativos baseando-se na sua Categoria de Espécie (Enum).
     */
    @Transactional(readOnly = true)
    public List<PacienteAnimal> buscarPorEspecie(CategoriaEspecie especie) {
        String jpql = "SELECT p FROM PacienteAnimal p WHERE p.especie = :especie AND p.ativo = true";
        TypedQuery<PacienteAnimal> query = entityManager.createQuery(jpql, PacienteAnimal.class);
        query.setParameter("especie", especie);
        return query.getResultList();
    }

    /**
     * Consulta avançada para localizar pacientes com imunizações críticas.
     * Retorna os animais que possuem vacinas atrasadas ou com vencimento dentro do intervalo estipulado.
     */
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
package br.com.clyvo.pet.repository;

import br.com.clyvo.pet.entity.AtividadeDiaria;
import br.com.clyvo.pet.exception.EntidadeNaoLocalizadaException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AtividadeDiariaDao {

    @PersistenceContext
    private EntityManager entityManager;

    public AtividadeDiaria salvar(AtividadeDiaria atividade) {
        entityManager.persist(atividade);
        return atividade;
    }

    public AtividadeDiaria atualizar(AtividadeDiaria atividade) {
        return entityManager.merge(atividade);
    }

    @Transactional(readOnly = true)
    public AtividadeDiaria buscarPorId(Long id) {
        AtividadeDiaria atividade = entityManager.find(AtividadeDiaria.class, id);
        if (atividade == null) {
            throw new EntidadeNaoLocalizadaException("Atividade Diária com ID " + id + " não foi localizada.");
        }
        return atividade;
    }

    public void remover(Long id) {
        AtividadeDiaria atividade = buscarPorId(id);
        entityManager.remove(atividade);
    }

    @Transactional(readOnly = true)
    public List<AtividadeDiaria> buscarTodos() {
        return entityManager.createQuery("SELECT a FROM AtividadeDiaria a ORDER BY a.dataRegistro DESC", AtividadeDiaria.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<AtividadeDiaria> buscarPorPaciente(Long pacienteId) {
        TypedQuery<AtividadeDiaria> query = entityManager.createQuery(
                "SELECT a FROM AtividadeDiaria a WHERE a.paciente.idPaciente = :pacienteId ORDER BY a.dataRegistro DESC",
                AtividadeDiaria.class);
        query.setParameter("pacienteId", pacienteId);
        return query.getResultList();
    }
}
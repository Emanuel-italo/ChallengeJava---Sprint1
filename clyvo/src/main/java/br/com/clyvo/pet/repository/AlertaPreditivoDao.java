package br.com.clyvo.pet.repository;

import br.com.clyvo.pet.entity.AlertaPreditivo;
import br.com.clyvo.pet.exception.EntidadeNaoLocalizadaException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AlertaPreditivoDao {

    @PersistenceContext
    private EntityManager entityManager;

    public AlertaPreditivo salvar(AlertaPreditivo alerta) {
        entityManager.persist(alerta);
        return alerta;
    }

    public AlertaPreditivo atualizar(AlertaPreditivo alerta) {
        return entityManager.merge(alerta);
    }

    @Transactional(readOnly = true)
    public AlertaPreditivo buscarPorId(Long id) {
        AlertaPreditivo alerta = entityManager.find(AlertaPreditivo.class, id);
        if (alerta == null) {
            throw new EntidadeNaoLocalizadaException("Alerta Preditivo com ID " + id + " não foi localizado.");
        }
        return alerta;
    }

    public void remover(Long id) {
        AlertaPreditivo alerta = buscarPorId(id);
        entityManager.remove(alerta);
    }

    @Transactional(readOnly = true)
    public List<AlertaPreditivo> buscarTodos() {
        return entityManager.createQuery("SELECT a FROM AlertaPreditivo a ORDER BY a.dataCriacao DESC", AlertaPreditivo.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<AlertaPreditivo> buscarPorPaciente(Long pacienteId) {
        TypedQuery<AlertaPreditivo> query = entityManager.createQuery(
                "SELECT a FROM AlertaPreditivo a WHERE a.paciente.idPaciente = :pacienteId ORDER BY a.dataCriacao DESC",
                AlertaPreditivo.class);
        query.setParameter("pacienteId", pacienteId);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<AlertaPreditivo> buscarPendentesPorPaciente(Long pacienteId) {
        TypedQuery<AlertaPreditivo> query = entityManager.createQuery(
                "SELECT a FROM AlertaPreditivo a WHERE a.paciente.idPaciente = :pacienteId AND a.enviado = false ORDER BY a.dataPrevista ASC",
                AlertaPreditivo.class);
        query.setParameter("pacienteId", pacienteId);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<AlertaPreditivo> buscarPendentes() {
        return entityManager.createQuery(
                        "SELECT a FROM AlertaPreditivo a WHERE a.enviado = false ORDER BY a.dataPrevista ASC",
                        AlertaPreditivo.class)
                .getResultList();
    }
}
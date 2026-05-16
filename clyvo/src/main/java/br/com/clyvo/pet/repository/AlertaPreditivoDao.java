package br.com.clyvo.pet.repository;

import br.com.clyvo.pet.entity.AlertaPreditivo;
import br.com.clyvo.pet.repository.dao.DaoGenerico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlertaPreditivoDao extends DaoGenerico<AlertaPreditivo> {

    public AlertaPreditivoDao() {
        super(AlertaPreditivo.class);
    }

    public List<AlertaPreditivo> buscarTodos() {
        EntityManager em = getEntityManager();
        try {
            // JPQL buscando todos e ordenando pelos mais recentes
            return em.createQuery("SELECT a FROM AlertaPreditivo a ORDER BY a.dataCriacao DESC", AlertaPreditivo.class)
                    .getResultList();
        } finally {
            em.close(); // Fecha o EntityManager para liberar o recurso
        }
    }

    public List<AlertaPreditivo> buscarPorPaciente(Long pacienteId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<AlertaPreditivo> query = em.createQuery(
                    "SELECT a FROM AlertaPreditivo a WHERE a.paciente.idPaciente = :pacienteId ORDER BY a.dataCriacao DESC",
                    AlertaPreditivo.class);
            query.setParameter("pacienteId", pacienteId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<AlertaPreditivo> buscarPendentesPorPaciente(Long pacienteId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<AlertaPreditivo> query = em.createQuery(
                    "SELECT a FROM AlertaPreditivo a WHERE a.paciente.idPaciente = :pacienteId AND a.enviado = false ORDER BY a.dataPrevista ASC",
                    AlertaPreditivo.class);
            query.setParameter("pacienteId", pacienteId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<AlertaPreditivo> buscarPendentes() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                            "SELECT a FROM AlertaPreditivo a WHERE a.enviado = false ORDER BY a.dataPrevista ASC",
                            AlertaPreditivo.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
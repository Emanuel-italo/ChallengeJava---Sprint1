package br.com.clyvo.pet.repository;

import br.com.clyvo.pet.entity.RegistroImunologico;
import br.com.clyvo.pet.exception.EntidadeNaoLocalizadaException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RegistroImunologicoDao {

    @PersistenceContext
    private EntityManager entityManager;

    public RegistroImunologico salvar(RegistroImunologico registro) {
        entityManager.persist(registro);
        return registro;
    }

    public RegistroImunologico atualizar(RegistroImunologico registro) {
        return entityManager.merge(registro);
    }

    @Transactional(readOnly = true)
    public RegistroImunologico buscarPorId(Long id) {
        RegistroImunologico registro = entityManager.find(RegistroImunologico.class, id);
        if (registro == null) {
            throw new EntidadeNaoLocalizadaException("Registo Imunológico com ID " + id + " não foi localizado.");
        }
        return registro;
    }

    public void remover(Long id) {
        RegistroImunologico registro = buscarPorId(id);
        entityManager.remove(registro);
    }

    @Transactional(readOnly = true)
    public List<RegistroImunologico> buscarTodos() {
        return entityManager.createQuery("SELECT r FROM RegistroImunologico r ORDER BY r.dataAplicacao DESC", RegistroImunologico.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<RegistroImunologico> buscarPorPaciente(Long pacienteId) {
        TypedQuery<RegistroImunologico> query = entityManager.createQuery(
                "SELECT r FROM RegistroImunologico r WHERE r.paciente.idPaciente = :pacienteId ORDER BY r.dataAplicacao DESC",
                RegistroImunologico.class);
        query.setParameter("pacienteId", pacienteId);
        return query.getResultList();
    }
}


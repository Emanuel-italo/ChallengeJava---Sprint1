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


}
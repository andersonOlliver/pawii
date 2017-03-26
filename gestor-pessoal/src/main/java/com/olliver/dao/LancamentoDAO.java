/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.dao;

import com.olliver.model.Lancamento;
import com.olliver.util.PersistenceManager;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author olliver
 */
public class LancamentoDAO {

    public Lancamento salvar(Lancamento lancamento) throws Exception {
        EntityManager entityManager = PersistenceManager.INSTANCE.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            if (lancamento.getId() == null) {
                entityManager.persist(lancamento);
            } else {
                entityManager.merge(lancamento);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }

        return lancamento;
    }

    public void excluir(Long id) {
        EntityManager entityManager = PersistenceManager.INSTANCE.getEntityManager();
        try {
            entityManager.getTransaction().begin();

            Lancamento lancamento = porId(id);

            if (lancamento != null) {
                entityManager.remove(lancamento);
                entityManager.getTransaction().commit();
            }
        } finally {
            entityManager.close();
        }
    }
    
    public Lancamento porId(Long id) {
        EntityManager entityManager = PersistenceManager.INSTANCE.getEntityManager();
        Lancamento retorno = null;

        try {
            retorno = entityManager.find(Lancamento.class, id);
        } finally {
            entityManager.close();
        }

        return retorno;
    }
    
    public List<Lancamento> todos() {
        EntityManager entityManager = PersistenceManager.INSTANCE.getEntityManager();
        List<Lancamento> retorno = null;

        try {
            retorno = entityManager.createQuery("from Lancamento", Lancamento.class).getResultList();
        } finally {
            entityManager.close();
        }

        return retorno;
    }
}

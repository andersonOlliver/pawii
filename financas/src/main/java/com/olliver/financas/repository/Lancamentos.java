/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.repository;

import com.olliver.financas.model.Lancamento;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public class Lancamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private EntityManager manager;

    @Inject
    public Lancamentos(EntityManager manager) {
        this.manager = manager;
    }
    
    public Lancamento porId(Long id) {
        return manager.find(Lancamento.class, id);
    }
    
    public List<Lancamento> todos() {
        return manager.createQuery("from Lancamento", Lancamento.class).getResultList();
    }

    public void adicionar(Lancamento lancamento) {
        manager.persist(lancamento);
    }

    public Lancamento guardar(Lancamento lancamento) {
        return manager.merge(lancamento);
    }

    public void remover(Lancamento lancamento) {
        manager.remove(lancamento);
    }
}

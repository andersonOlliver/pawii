/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.repository;

import com.olliver.financas.model.Lancamento;
import com.olliver.financas.model.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
    
    public List<Lancamento> periodo(Date inicio, Date fim, Usuario usuario){
        System.out.println("início = "+ inicio);
        System.out.println("fim = "+ fim);
        TypedQuery query = manager.createQuery("select l from Lancamento l where l.usuario=:usuario and l.dataCadastro BETWEEN :inicio and :fim", Lancamento.class);
        query.setParameter("usuario", usuario);
        query.setParameter("inicio", inicio);
        query.setParameter("fim", fim);
        return query.getResultList();
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

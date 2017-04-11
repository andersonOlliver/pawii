/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.repository;

import com.olliver.financas.model.Lancamento;
import com.olliver.financas.model.TipoLancamento;
import com.olliver.financas.model.Usuario;
import com.olliver.financas.repository.util.LancamentoCategoria;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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

    public List<Lancamento> periodo(Date inicio, Date fim, Usuario usuario) {
        TypedQuery query = manager.createQuery("select l from Lancamento l INNER JOIN FETCH l.categoria where l.usuario=:usuario and l.dataCadastro BETWEEN :inicio and :fim ORDER BY l.dataCadastro ASC", Lancamento.class);
        query.setParameter("usuario", usuario);
        query.setParameter("inicio", inicio);
        query.setParameter("fim", fim);
        return query.getResultList();
    }

    public Double situacao(Date inicio, Date fim, Usuario usuario, TipoLancamento tipo) {
        Query query = manager.createQuery("select SUM(l.valor) from Lancamento l where l.usuario=:usuario and l.tipo=:tipo and l.dataCadastro BETWEEN :inicio and :fim");
        query.setParameter("usuario", usuario);
        query.setParameter("inicio", inicio);
        query.setParameter("fim", fim);
        query.setParameter("tipo", tipo);
        return ((Number) query.getSingleResult()).doubleValue();
    }

//    public List<LancamentoCategoria> despesaCategoriaPeriodo(Date inicio, Date fim, Usuario usuario) {
//        Criteria criteria = criarCriteria();
//        
//        criteria.add(Restrictions.ilike(propertyName, fim));
//        
//    }
    public List<LancamentoCategoria> despesaCategoriaPeriodo(Date inicio, Date fim, Usuario usuario) {
        TypedQuery<LancamentoCategoria> query = manager.createQuery("SELECT NEW com.olliver.financas.repository.util.LancamentoCategoria(L.categoria.descricao, SUM(L.valor)) FROM Lancamento AS L WHERE L.usuario=:usuario and L.tipo=:tipo and L.dataCadastro BETWEEN :inicio and :fim GROUP BY L.categoria ORDER BY L.categoria ASC ", LancamentoCategoria.class);
        query.setParameter("usuario", usuario);
        query.setParameter("inicio", inicio);
        query.setParameter("fim", fim);
        query.setParameter("tipo", TipoLancamento.DESPESA);
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

//    private Criteria criarCriteria() {
//        Session session = manager.unwrap(Session.class);
//        Criteria criteria = session.createCriteria(Lancamento.class);
//        criteria.createAlias("", string1)
//        return criteria;
//    }
}

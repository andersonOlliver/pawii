/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.olliver.financas.repository;

import com.olliver.financas.model.Categoria;
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
public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private EntityManager manager;

    @Inject
    public Categorias(EntityManager manager) {
        this.manager = manager;
    }
    
    public Categoria porId(Long id) {
        return manager.find(Categoria.class, id);
    }
    
    public List<Categoria> todos() {
        return manager.createQuery("from Categoria", Categoria.class).getResultList();
    }

    public void adicionar(Categoria categoria) {
        manager.persist(categoria);
    }

    public Categoria guardar(Categoria categoria) {
        return manager.merge(categoria);
    }

    public void remover(Categoria categoria) {
        manager.remove(categoria);
    }

}

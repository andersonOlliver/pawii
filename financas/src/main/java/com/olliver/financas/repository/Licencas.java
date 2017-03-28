/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.olliver.financas.repository;

import com.olliver.financas.model.Licenca;
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
public class Licencas implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private EntityManager manager;

    @Inject
    public Licencas(EntityManager manager) {
        this.manager = manager;
    }
    
    public Licenca porId(Long id) {
        return manager.find(Licenca.class, id);
    }
    
    public List<Licenca> todos() {
        return manager.createQuery("from Licenca", Licenca.class).getResultList();
    }

    public void adicionar(Licenca licenca) {
        manager.persist(licenca);
    }

    public Licenca guardar(Licenca licenca) {
        return manager.merge(licenca);
    }

    public void remover(Licenca licenca) {
        manager.remove(licenca);
    }

}

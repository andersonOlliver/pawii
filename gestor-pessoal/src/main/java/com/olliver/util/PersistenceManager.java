/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author t1071801
 */
public enum PersistenceManager {
    INSTANCE;

    private EntityManagerFactory emFactory;

    private PersistenceManager() {
        this.emFactory = Persistence.createEntityManagerFactory("gestaoPU");
    }
    
    public EntityManager getEntityManager(){
        return emFactory.createEntityManager();
    }
    
    public void close(){
        emFactory.close();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.dao;

import com.olliver.model.Usuario;
import com.olliver.util.PersistenceManager;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public abstract class UsuarioDAO implements Serializable {

    public static Usuario entrar(String login, String password){
        EntityManager entityManager = PersistenceManager.INSTANCE.getEntityManager();
        
        Usuario retorno = null;
        
        try{
            Query query = entityManager.createQuery("select u from Usuario u where u.login=:login and u.password=:password", Usuario.class);
            query.setParameter("login", login);
            query.setParameter("password", password);
            retorno = (Usuario) query.getSingleResult();
        }finally{
            entityManager.close();
        }
        return retorno;
    }

    public static Usuario entrar(String login){
        EntityManager entityManager = PersistenceManager.INSTANCE.getEntityManager();
        
        Usuario retorno = null;
        
        try{
            Query query = entityManager.createQuery("select u from Usuario u where u.login=:login", Usuario.class);
            query.setParameter("login", login);
            retorno = (Usuario) query.getSingleResult();
        }finally{
            entityManager.close();
        }
        return retorno;
    }
    
    public static List<Usuario> todos(){
        EntityManager entityManager = PersistenceManager.INSTANCE.getEntityManager();
        
        List<Usuario> retorno = null;

        try {
            retorno = entityManager.createQuery("from Usuario", Usuario.class).getResultList();
        } finally {
            entityManager.close();
        }

        return retorno;
    }
}

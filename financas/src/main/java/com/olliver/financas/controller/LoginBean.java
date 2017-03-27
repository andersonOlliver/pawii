/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.controller;

import com.olliver.financas.model.Usuario;
import com.olliver.financas.service.ConsultaLogin;
import com.olliver.financas.service.NegocioException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author olliver
 */
@Named
@ViewScoped
public class LoginBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private ConsultaLogin consulta;
    private String email;
    private String senha;
    private Usuario usuario;
    
    public void entrar(){
        try {
            usuario = consulta.login(email, senha);
        } catch (NegocioException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("entrou");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    
    
}

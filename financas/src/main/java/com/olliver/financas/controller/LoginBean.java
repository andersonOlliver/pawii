/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.controller;

import com.olliver.financas.model.Usuario;
import com.olliver.financas.service.ConsultaLogin;
import com.olliver.financas.service.NegocioException;
import com.olliver.financas.util.jsf.FacesUtil;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author olliver
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ConsultaLogin consulta;
    private String email;
    private String senha;
    private Usuario usuario;

    public String entrar() {
        try {
            usuario = consulta.login(email, senha);
            FacesUtil.addInfoMessageRedirect("Bem-vindo " + usuario.getNome());
            return "/acesso/index.paw?faces-redirect=true";
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
        return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/entrar?faces-redirect=true";
    }

    public boolean isLogado() {
        return usuario != null;
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

    public Usuario getUsuario() {
        return usuario;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.controller;

import com.olliver.financas.model.Usuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author aluno
 */
@ManagedBean
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

    private Usuario usuario;

    public void inicializar() {
        this.usuario = new Usuario();
    }

    public void salvar() {
        System.out.println("Salvo usuario = " + usuario);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}

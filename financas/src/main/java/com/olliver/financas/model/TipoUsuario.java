/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.model;

/**
 *
 * @author olliver
 */
public enum TipoUsuario {

    COMUM("Comum"), ADMINISTRADOR("Administrador");

    private String descricao;

    private TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

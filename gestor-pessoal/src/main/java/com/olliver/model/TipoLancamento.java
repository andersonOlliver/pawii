/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.model;

/**
 *
 * @author olliver
 */
public enum TipoLancamento {
    
    DESPESA("Despesa"), RECEITA("Receita");
    
    private String descrição;

    private TipoLancamento(String descrição) {
        this.descrição = descrição;
    }

    public String getDescrição() {
        return descrição;
    }
}

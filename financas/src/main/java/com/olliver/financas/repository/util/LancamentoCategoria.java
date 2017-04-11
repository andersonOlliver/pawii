package com.olliver.financas.repository.util;

import java.io.Serializable;

public class LancamentoCategoria implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String nomeCategoria;
    private int gasto;

    public LancamentoCategoria(String nomeCategoria, Double gasto) {
        this.nomeCategoria = nomeCategoria;
        this.gasto = ((Number) gasto).intValue();
    }
    
    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public int getGasto() {
        return gasto;
    }

    public void setGasto(int gasto) {
        this.gasto = gasto;
    }

    
    
    public int getGastoInteiro() {
        return ((Number) gasto).intValue();
    }

    

    @Override
    public String toString() {
        return "LancamentoCategoria{" + "nomeCategoria=" + nomeCategoria + ", gasto=" + gasto + '}';
    }
    
    
    
    
}

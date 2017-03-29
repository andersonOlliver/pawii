/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.repository.util;

/**
 *
 * @author Administrador
 */
public class Situacao {
    
    private Double totalDebito;
    private Double totalCredito;

    public Situacao(Double totalDebito, Double totalCredito) {
        this.totalDebito = totalDebito;
        this.totalCredito = totalCredito;
    }

    public Double getTotalDebito() {
        return totalDebito;
    }

    public void setTotalDebito(Double totalDebito) {
        this.totalDebito = totalDebito;
    }

    public Double getTotalCredito() {
        return totalCredito;
    }

    public void setTotalCredito(Double totalCredito) {
        this.totalCredito = totalCredito;
    }

    
    
    @Override
    public String toString() {
        return "Situacao{" + "totalDebito=" + totalDebito + ", totalCredito=" + totalCredito + '}';
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.controller;

import com.olliver.financas.model.Lancamento;
import com.olliver.financas.repository.Lancamentos;
import com.olliver.financas.service.CadastroLancamento;
import com.olliver.financas.service.NegocioException;
import com.olliver.financas.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
@Named
@ViewScoped
public class GerenciaLancamentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Lancamentos repositorio;
    @Inject
    private CadastroLancamento cadastro;
    @Inject
    private LoginBean autenticacao;

    private List<Lancamento> lancamentos;
    private Lancamento lancamento;

    public void consultar() {
        this.lancamentos = repositorio.todos();
    }

    public void salvar() {
        try {
            if(lancamento.getUsuario() == null){
                lancamento.setUsuario(autenticacao.getUsuario());
            }
            
            cadastro.salvar(lancamento);
            this.novoItem();
            FacesUtil.addInfoMessage("Lançamento inserido com sucesso!");
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public void novoItem(){
        lancamento = new Lancamento();
    }

    //GETTER SETTER
    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

}

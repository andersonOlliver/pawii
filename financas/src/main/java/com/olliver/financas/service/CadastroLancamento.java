/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.service;

import com.olliver.financas.model.Lancamento;
import com.olliver.financas.repository.Lancamentos;
import com.olliver.financas.util.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public class CadastroLancamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Lancamentos repositorio;

    @Transactional
    public void salvar(Lancamento lancamento) throws NegocioException {
        if (lancamento == null) {
            throw new NegocioException("Lançamento inválido!");
        }
//        System.out.println(lancamento);
        repositorio.guardar(lancamento);
    }

    @Transactional
    public void excluir(Lancamento lancamento) throws NegocioException {
        lancamento = this.repositorio.porId(lancamento.getId());

        this.repositorio.remover(lancamento);
    }
}

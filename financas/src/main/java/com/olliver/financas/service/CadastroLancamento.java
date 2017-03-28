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

/**
 *
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public class CadastroLancamento implements Serializable {

    private static final long serialVersionUID = 1L;

    private Lancamentos repositorio;

    @Transactional
    public void salvar(Lancamento lancamento) throws NegocioException {
        if (lancamento == null) {
            throw new NegocioException("Lançamento inválido!");
        }
        
        repositorio.adicionar(lancamento);
    }
}

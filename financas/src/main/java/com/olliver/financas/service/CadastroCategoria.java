/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.olliver.financas.service;

import com.olliver.financas.model.Categoria;
import com.olliver.financas.repository.Categorias;
import com.olliver.financas.util.Transactional;
import java.io.Serializable;

/**
 *
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public class CadastroCategoria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Categorias repositorio;

    @Transactional
    public void salvar(Categoria categoria) throws NegocioException {
        if (categoria == null) {
            throw new NegocioException("Categoria inválida!");
        }
        
        repositorio.adicionar(categoria);
    }

}

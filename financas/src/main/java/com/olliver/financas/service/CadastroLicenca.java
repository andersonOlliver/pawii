/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.olliver.financas.service;

import com.olliver.financas.model.Licenca;
import com.olliver.financas.repository.Licencas;
import com.olliver.financas.util.Transactional;
import java.io.Serializable;

/**
 *
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public class CadastroLicenca implements Serializable {

    private static final long serialVersionUID = 1L;

    private Licencas repositorio;

    @Transactional
    public void salvar(Licenca licenca) throws NegocioException {
        if (licenca == null) {
            throw new NegocioException("Licença inválida!");
        }
        
        repositorio.adicionar(licenca);
    }

}

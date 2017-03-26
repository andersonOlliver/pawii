/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.service;

import com.olliver.financas.model.Usuario;
import com.olliver.financas.repository.Usuarios;
import com.olliver.financas.util.Transactional;
import javax.inject.Inject;

/**
 *
 * @author olliver
 */
public class CadastroUsuario {

    @Inject
    private Usuarios usuarios;

    @Transactional
    public void salvar(Usuario usuario) throws NegocioException {
        if (usuario == null) {
            throw new NegocioException("Usuário inválido!");
        }
        
        this.usuarios.adicionar(usuario);
    }
}

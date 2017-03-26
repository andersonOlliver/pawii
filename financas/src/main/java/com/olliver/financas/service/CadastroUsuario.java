/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.service;

import com.olliver.financas.model.Usuario;
import com.olliver.financas.repository.Usuarios;
import com.olliver.financas.util.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.inject.Inject;

/**
 *
 * @author olliver
 */
public class CadastroUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private Usuarios usuarios;

    @Transactional
    public void salvar(Usuario usuario) throws NegocioException {
        if (usuario == null) {
            throw new NegocioException("Usuário inválido!");
        }
        if (usuario.getDataCadastro() == null) {
            usuario.setDataCadastro(LocalDateTime.now());
        }

        this.usuarios.adicionar(usuario);
    }
}

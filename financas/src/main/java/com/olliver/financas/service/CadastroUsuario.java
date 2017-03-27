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
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author olliver
 */
public class CadastroUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;

    @Transactional
    public void salvar(Usuario usuario, String senhaConfirmacao) throws NegocioException {
        if (usuario == null) {
            throw new NegocioException("Usuário inválido!");
        } else {
            try {
                Usuario aux = usuarios.porEmail(usuario.getEmail());
                throw new NegocioException("Email já cadastrado!");
            } catch (NoResultException ne) {
                if (!usuario.getSenha().equals(senhaConfirmacao)) {
                    throw new NegocioException("Senhas não conferem!");
                }else if(usuario.getDataNascimento().after(new Date())){
                    throw new NegocioException("Data de nascimento não pode estar no futuro!");
                }
                if (usuario.getDataCadastro() == null) {
                    usuario.setDataCadastro(LocalDateTime.now());
                }

                this.usuarios.adicionar(usuario);
            }

        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.service;

import com.olliver.financas.model.Usuario;
import com.olliver.financas.repository.Usuarios;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author olliver
 */
public class ConsultaLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;

    public Usuario login(String email, String senha) throws NegocioException {
        if(!email.isEmpty()){
            Usuario aux = usuarios.porEmail(email);
            if(aux == null){
                throw new NegocioException("Não encontrado!");
            }else if(aux.getSenha().equals(senha)){
                return aux;
            }else{
                throw new NegocioException("Senha não confere!");
            }
        }else{
            throw new NegocioException("Email inválido!");
        }
    }

}

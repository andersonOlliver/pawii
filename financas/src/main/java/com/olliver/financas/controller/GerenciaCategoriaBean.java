/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.controller;

import com.olliver.financas.model.Categoria;
import com.olliver.financas.repository.Categorias;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author olliver
 */
public class GerenciaCategoriaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private Categorias repositorioCategoria;
    
    private List<Categoria> categorias;
    private Categoria categoria;
    
    public void consultar(){
        this.categorias = repositorioCategoria.todos();
    }
}

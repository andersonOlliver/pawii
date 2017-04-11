/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.controller;

import com.olliver.financas.service.CadastroUsuario;
import com.olliver.financas.service.NegocioException;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.omnifaces.util.Utils;

/**
 *
 * @author olliver
 */
@Named
@RequestScoped
public class CadastroImagemBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private LoginBean autenticacao;

    @Inject
    private CadastroUsuario cadastroUsuario;

    private Part file;
    private byte[] content;

    public void read() throws IOException {
        content = Utils.toByteArray(file.getInputStream());
        autenticacao.getUsuario().setImagemPerfil(content);
    }

    public void salvar() {
        System.out.println(autenticacao.getUsuario().getImagemPerfil().length);
        try {
            this.autenticacao.setUsuario(
                    this.cadastroUsuario.salvar(autenticacao.getUsuario())
            );
        } catch (NegocioException ex) {
            Logger.getLogger(CadastroImagemBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public byte[] getContent() {
        return content;
    }

}

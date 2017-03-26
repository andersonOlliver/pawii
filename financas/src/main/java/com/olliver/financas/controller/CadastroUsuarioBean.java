/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.controller;

import com.olliver.financas.model.Usuario;
import com.olliver.financas.service.CadastroUsuario;
import com.olliver.financas.service.NegocioException;
import com.olliver.financas.util.jsf.FacesUtil;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author aluno
 */
@ManagedBean
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroUsuario cadastro;
    
    private Usuario usuario;
    private String labelAceite;
    private String textoLicença;

    public void inicializar() {
        this.usuario = new Usuario();
        this.labelAceite = "Eu estou de acordo com os termos de uso";
        this.textoLicença = "asdfkljaçsl fkjasçl fjasflkjsafkjasçlfjasçlfk jçslfd jsaça lksjfçlask jfçsk dfja fkjsaçfk jsaflkjsadçlf kjsçflkasjç askjfç sakdjf sakljfçlsak jaçslf jasçlkfj saçdlkfj asçlkfjasçldfj asçlkf jasçfkljsaçf kjsadçfl asjfçlkaj çlj çlsjf çl";
    }

    public void salvar() {
        this.usuario.setDataCadastro(LocalDateTime.now());
        try {
            this.cadastro.salvar(usuario);
            FacesUtil.addInfoMessage("Cadastro realizado com sucesso!");
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
        System.out.println("Salvo usuario = " + usuario);
    }    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getLabelAceite() {
        return labelAceite;
    }

    public String getTextoLicença() {
        return textoLicença;
    }

}

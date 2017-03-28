/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.controller;

import com.olliver.financas.model.Licenca;
import com.olliver.financas.model.Usuario;
import com.olliver.financas.repository.Licencas;
import com.olliver.financas.service.CadastroUsuario;
import com.olliver.financas.service.NegocioException;
import com.olliver.financas.util.jsf.FacesUtil;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author aluno
 */
@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroUsuario cadastro;
    @Inject
    private Licencas licencaRepositorio;
    @Inject
    private LoginBean autenticacao;

    private Usuario usuario;
    private Licenca licenca;

    @NotEmpty
    private String confimaSenha;
    private String labelAceite;
    private String textoLicença;

    public void inicializar() {
        this.usuario = new Usuario();
        this.licenca = licencaRepositorio.porId(1L);
        this.labelAceite = "Li e concordo com os termos de uso";
        
    }

    public String salvar() {
        try {
            if (usuario.isAceite()) {
                usuario.setLicenca(licenca);
            }
            this.cadastro.salvar(usuario, confimaSenha);
            return this.entrar();
//            FacesUtil.addInfoMessage("Cadastro realizado com sucesso!");
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
        return null;
    }

    private String entrar() {
        this.autenticacao.setEmail(usuario.getEmail());
        this.autenticacao.setSenha(usuario.getSenha());
        return this.autenticacao.entrar();
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

    public String getConfimaSenha() {
        return confimaSenha;
    }

    public void setConfimaSenha(String confimaSenha) {
        this.confimaSenha = confimaSenha;
    }

    public Licenca getLicenca() {
        return licenca;
    }

}

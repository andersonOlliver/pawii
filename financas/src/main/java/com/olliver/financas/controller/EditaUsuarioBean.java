package com.olliver.financas.controller;

import com.olliver.financas.model.Usuario;
import com.olliver.financas.service.CadastroUsuario;
import com.olliver.financas.service.NegocioException;
import com.olliver.financas.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class EditaUsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private LoginBean autenticacao;
    @Inject
    private CadastroUsuario cadastro;
    
    private Usuario usuario;
    private String senhaConfirmacao;
    
    public void pegarUsuario(){
        this.usuario = autenticacao.getUsuario();
    }

    public void salvar(){
        try {
            this.cadastro.salvar(usuario);
            FacesUtil.addInfoMessage("Alterado com sucesso");
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.controller;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.omnifaces.util.Utils;

/**
 *
 * @author olliver
 */
@Named
@RequestScoped
public class CadastroImagemBean {
    
    private Part file;
    private byte[] content;
    
    public void read() throws IOException{
        content = Utils.toByteArray(file.getInputStream());
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


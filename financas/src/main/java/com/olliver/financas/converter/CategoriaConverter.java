package com.olliver.financas.converter;

import com.olliver.financas.model.Categoria;
import com.olliver.financas.repository.Categorias;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author Administrador
 */
@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

    @Inject //deve funcionar graças ao OmniFaces
    private Categorias categorias;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Categoria retorno = null;
        
        if(value != null && !"".equals(value)){
            retorno = this.categorias.porId(new Long(value));
        }
        
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            return ((Categoria) value).getId().toString();
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.olliver.model.Usuario;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public abstract class Login {

    private static List<Usuario> users;

    public static Usuario getUsuario(String login) {
        System.out.println(login);
        Usuario aux = new Usuario(login);
        if (users.contains(aux)) {
            return users.get(users.indexOf(aux));
        }
        return null;
    }

    public static List<Usuario> getUsuarios() {
        users = new ArrayList<>();
        users.add(new Usuario(1, "admin", "123"));
        users.add(new Usuario(2, "root", "root"));
        users.add(new Usuario(3, "dafault", "dafault"));
        return users;
    }

}

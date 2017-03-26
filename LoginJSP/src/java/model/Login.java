/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
public abstract class Login {

    private static List<User> users;

    public static User getUser(String login) {
        System.out.println(login);
        User aux = new User(login);
        if (users.contains(aux)) {
            return users.get(users.indexOf(aux));
        }
        return null;
    }

    public static List<User> getUsers() {
        users = new ArrayList<>();
        users.add(new User(1, "admin", "123"));
        users.add(new User(2, "root", "root"));
        users.add(new User(3, "dafault", "dafault"));
        return users;
    }

}

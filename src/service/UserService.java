/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.User;

/**
 *
 * @author lenovo
 */
public class UserService extends AbstractFacade<User> {

    public UserService() {
        super(User.class);
    }

    public Object[] seConnecte(User user) {
        User loadeUser = find(user.getLogin());
        if (loadeUser == null) {
            return new Object[]{-1, null};
        } else if (!loadeUser.getPassword().equals(user.getPassword())) {
            return new Object[]{-2, null};
        } else if (loadeUser.getBlocked() == 1) {
            return new Object[]{-3, null};
        } else {
            return new Object[]{1, loadeUser};
        }
    }

    
    
 
}

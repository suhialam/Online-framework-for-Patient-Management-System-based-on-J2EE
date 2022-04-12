/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.LoginDAO;
import entity.User;

/**
 *
 * @author suhail
 */
public class LoginService {
    private LoginDAO loginDAO;

    public LoginService() {
        loginDAO = new LoginDAO();
    }
    
    
    public boolean tryLogin(User user){
        return loginDAO.tryLogin(user);
    }
}

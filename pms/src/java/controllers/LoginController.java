/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.User;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author babu
 */
@ManagedBean(name = "loginController")
@RequestScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{anyUser}") // anyUser is typecasted to user (user in next line).
    private User user;// this line will be the next line of Managed property(Bean class)
    // write comment for the managed property explanation

    private String message;
    private String cssClass = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String tryLogin() {
        boolean status = false;
/*
        LoginService loginService = new LoginService();
        status = loginService.tryLogin(user);

        if (status) {
            message = "this user can be logged in.";
            myClass = "success-message";

            return "home?faces-redirect=true";

        } else {
            message = "Incorrect user name or password.";
            // to show the message in login page. in red color.
            myClass = "failure-message";

            return null;

        }
  */
return null;
    }

}
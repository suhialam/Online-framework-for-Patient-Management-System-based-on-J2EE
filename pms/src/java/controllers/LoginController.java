/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.User;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import services.LoginService;
import util.SessionUtils;

/**
 *
 * @author suhail
 */
@ManagedBean(name = "loginController")
@SessionScoped
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

        String redirectionPath = "";
        LoginService loginService = new LoginService();
        status = loginService.tryLogin(user);
        //System.out.println(user.getDisplayName());
        if (status == true) {
            message = "this user can be logged in.";
            cssClass = "success-message";

            //FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            //FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "index.xtml");
            //Object obj = (Object) user;
            HttpSession session = SessionUtils.getSession();
            
            user.setDisplayName("Wellcome " + user.getDisplayName());
            session.setAttribute("user", user);

            redirectionPath = "home?faces-redirect=true";

        } else {
            message = "Incorrect user name or password.";
            cssClass = "failure-message";

            redirectionPath = null;
        }

        return redirectionPath;
    }

    public void logout() {
        System.out.println("this is logout method");
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

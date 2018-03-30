package util;

import entity.User;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        //return session.getAttribute("user").toString();
        String displayname = ((User) session.getAttribute("user")).getDisplayName();
        return displayname;
    }

    public static String getUserId() {
        HttpSession session = getSession();
        String userId = "";
        if (session != null) {
            userId = ((User) session.getAttribute("user")).getUserId() + "";
        }

        return userId;
    }
}

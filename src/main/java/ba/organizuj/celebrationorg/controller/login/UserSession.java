package ba.organizuj.celebrationorg.controller.login;

import ba.organizuj.celebrationorg.ejb.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Logger;

public enum UserSession {

    USER("USER_SESSION_KEY");

    private String sessionKey;

    UserSession(String sessionKey){
        this.sessionKey = sessionKey;
    }

    public void addToSession(User user, HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute(sessionKey) == null){
            session.setAttribute(sessionKey, user);
        }
    }

    public User getFromSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (User) session.getAttribute(sessionKey);
    }

    public void invalidateSession(HttpServletRequest request, HttpServletResponse response){
        try{
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("login");
        }catch (IOException e){
            Logger.getLogger(UserSession.class.getName()).warning(e.getMessage());
        }
    }
}

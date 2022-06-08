package ba.organizuj.celebrationorg.controller.login;

import ba.organizuj.celebrationorg.controller.router.Routes;
import ba.organizuj.celebrationorg.ejb.user.entity.User;
import ba.organizuj.celebrationorg.ejb.user.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//http://localhost:8080/ime_aplikacije/authentication
@WebServlet(name = "AuthenticationControllerServlet", value = "/authentication")
public class AuthenticationControllerServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.login(username, password);
        if(user == null){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(Routes.LOGIN);
            requestDispatcher.forward(request, response);
        }else{
            UserSession.USER.addToSession(user, request);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(Routes.DASHBOARD_ACCES);
            requestDispatcher.forward(request, response);
        }
    }
}

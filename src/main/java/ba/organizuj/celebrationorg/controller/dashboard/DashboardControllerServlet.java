package ba.organizuj.celebrationorg.controller.dashboard;

import ba.organizuj.celebrationorg.controller.login.UserSession;
import ba.organizuj.celebrationorg.controller.router.Routes;
import ba.organizuj.celebrationorg.ejb.user.entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//http://localhost:8080/ime_aplikacije/login
//http://localhost:8080/ime_aplikacije/authentication/login.jsp
@WebServlet(name = "dashboardControllerServlet", value = "/dashboardAccess")
public class DashboardControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userSession = UserSession.USER.getFromSession(request);
        if(userSession != null){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(Routes.DASHBOARD);
            requestDispatcher.forward(request, response);
        }else{
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(Routes.LOGIN);
            requestDispatcher.forward(request, response);
        }
    }
}

package ba.organizuj.celebrationorg.controller.logout;

import ba.organizuj.celebrationorg.controller.router.Routes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "logoutControllerServlet", value = "/logout")
public class LogoutControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Controll", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        request.getSession().invalidate();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(Routes.LOGIN);
        requestDispatcher.forward(request, response);
    }
}

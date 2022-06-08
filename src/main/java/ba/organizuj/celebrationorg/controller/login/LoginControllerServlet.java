package ba.organizuj.celebrationorg.controller.login;

import ba.organizuj.celebrationorg.controller.router.Routes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Na kojim URLs sam ja {@link LoginControllerServlet} dostupan ?
 * <p>
 *     Dostupan si na :
 *     <li>1. context path aplikacije : http://localhost:8080/CelebrationOrg-1.0-SNAPSHOT/</li>
 *     <li>2. http://localhost:8080/CelebrationOrg-1.0-SNAPSHOT/login</li>
 * </p>
 */
@WebServlet(name = "loginControllerServlet", value = "/login")
public class LoginControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(Routes.LOGIN);
        requestDispatcher.forward(request, response);
    }
}

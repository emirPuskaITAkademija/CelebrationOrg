package ba.organizuj.celebrationorg.controller.dashboard.celebration;

import ba.organizuj.celebrationorg.controller.login.UserSession;
import ba.organizuj.celebrationorg.controller.router.Routes;
import ba.organizuj.celebrationorg.ejb.celebration.entity.Celebration;
import ba.organizuj.celebrationorg.ejb.celebration.service.CelebrationService;
import ba.organizuj.celebrationorg.ejb.user.entity.User;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "celebrationControllerServlet", value = "/celebration")
public class CelebrationControllerServlet extends HttpServlet {

    @Inject
    private CelebrationService celebrationService;

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
            List<Celebration> celebrationList = celebrationService.findByUserCreator(userSession);
            request.setAttribute("proslave", celebrationList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(Routes.CELEBRATION);
            requestDispatcher.forward(request, response);
        }else{
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(Routes.LOGIN);
            requestDispatcher.forward(request, response);
        }
    }
}

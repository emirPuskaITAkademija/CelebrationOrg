package ba.organizuj.celebrationorg.controller.dashboard.celebration;

import ba.organizuj.celebrationorg.controller.login.UserSession;
import ba.organizuj.celebrationorg.controller.router.Routes;
import ba.organizuj.celebrationorg.ejb.celebration.entity.Celebration;
import ba.organizuj.celebrationorg.ejb.celebration.service.CelebrationService;
import ba.organizuj.celebrationorg.ejb.user.entity.User;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AddCelebrationServlet", value = "/addCelebration")
public class AddCelebrationServlet extends HttpServlet {
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
            String celebrationName = request.getParameter("celebrationName");
            String celebrationDate = request.getParameter("celebrationDate");
            Celebration celebration = new Celebration();
            celebration.setName(celebrationName);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //danasnji
            Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
            try{
                date = format.parse(celebrationDate);
            }catch (ParseException e){
                getServletContext().log("DATE parse: " + e.getMessage());
            }
            celebration.setCelebrationDate(date);
            celebration.setUserCreator(userSession);
            celebrationService.create(celebration);
            //na isti page
            List<Celebration> celebrations = celebrationService.findByUserCreator(userSession);
            request.setAttribute("proslave", celebrations);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(Routes.CELEBRATION);
            requestDispatcher.include(request, response);
        }else{
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(Routes.LOGIN);
            requestDispatcher.forward(request, response);
        }
    }
}

package ba.organizuj.celebrationorg.controller.register;

import ba.organizuj.celebrationorg.controller.router.Routes;
import ba.organizuj.celebrationorg.ejb.town.entity.Town;
import ba.organizuj.celebrationorg.ejb.town.service.TownService;
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
import java.util.List;

//http://localhost:8080/ime_aplikacije/register
@WebServlet(name = "registerControllerServlet", value = "/register")
public class RegisterControllerServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Inject
    private TownService townService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegisterModel registerModel = RegisterModel.builder()
                .name(request.getParameter("name"))
                .surname(request.getParameter("surname"))
                .username(request.getParameter("username"))
                .plainPassword(request.getParameter("password"))
                .contact(request.getParameter("contact"))
                .email(request.getParameter("email"))
                .town(request.getParameter("town"))
                .build();

        RegisterController registerController = new RegisterController(userService, registerModel);
        if(registerController.isValidRegisterModel()){
            if(registerController.isUsernameOccupied()){
                List<Town> townList = townService.findAll();
                request.setAttribute("townList", townList);
                request.setAttribute("message", String.format("Username '%s' is already in use", registerModel.getUsername()));
                RequestDispatcher registerDispatcher = request.getRequestDispatcher(Routes.REGISTRATION_FORM);
                registerDispatcher.forward(request, response);
            }else{
                User user = userService.register(registerModel);
                if(user != null){
                    RequestDispatcher registerDispatcher = request.getRequestDispatcher(Routes.LOGIN);
                    registerDispatcher.forward(request, response);
                }else{
                    RequestDispatcher registerDispatcher = request.getRequestDispatcher(Routes.REGISTRATION_FORM);
                    registerDispatcher.forward(request, response);
                }
            }
        }else{
            List<Town> townList = townService.findAll();
            request.setAttribute("townList", townList);
            request.setAttribute("emptyFieldMessage", String.format("Popunite sva polja prilikom registracije", registerModel.getUsername()));
            RequestDispatcher registerDispatcher = request.getRequestDispatcher(Routes.REGISTRATION_FORM);
            registerDispatcher.forward(request, response);
        }

    }
}

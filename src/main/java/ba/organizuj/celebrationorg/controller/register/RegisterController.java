package ba.organizuj.celebrationorg.controller.register;

import ba.organizuj.celebrationorg.ejb.user.entity.User;
import ba.organizuj.celebrationorg.ejb.user.service.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterController {

    private final UserService userService;
    private final RegisterModel registerModel;

    public boolean isUsernameOccupied(){
        User user = userService.findByUsernameOrNull(registerModel.getUsername());
        return user != null;
    }

    public boolean isValidRegisterModel(){
        return !registerModel.getName().isEmpty()
                && !registerModel.getSurname().isEmpty()
                && !registerModel.getUsername().isEmpty()
                && !registerModel.getPlainPassword().isEmpty()
                && !registerModel.getEmail().isEmpty()
                && !registerModel.getTown().isEmpty()
                && !registerModel.getContact().isEmpty();
    }
}

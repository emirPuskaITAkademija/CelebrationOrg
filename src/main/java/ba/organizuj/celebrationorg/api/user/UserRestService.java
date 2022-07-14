package ba.organizuj.celebrationorg.api.user;

import ba.organizuj.celebrationorg.controller.register.RegisterController;
import ba.organizuj.celebrationorg.controller.register.RegisterModel;
import ba.organizuj.celebrationorg.ejb.user.entity.User;
import ba.organizuj.celebrationorg.ejb.user.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

//http://localhost:8080/CelebrationOrg-1.0-SNAPSHOT/api/users  HTTP GET
@Path("/users")
public class UserRestService {

    @Inject
    private UserService userService;


    //http://localhost:8080/CelebrationOrg-1.0-SNAPSHOT/api/users  GET
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDto> getUsers() {
       /* List<User> userList = userService.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : userList){
            UserDto userDto = UserDto.toUserDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;*/
        return userService.findAll()
                .stream()
                .map(UserDto::toUserDto)
                .collect(Collectors.toList());
    }

    //http://localhost:8080/CelebrationOrg-1.0-SNAPSHOT/api/users/1 GET
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDto getUser(@PathParam("id") int id) {
        User user = userService.find(id);
        UserDto userDto = UserDto.toUserDto(user);
        return userDto;
    }

    /**
     * <li>1. http://localhost:8080/CelebrationOrg-1.0-SNAPSHOT/api/users</li>
     * <li>2. POST http request</li>
     * <li>
     * HttpRequest format
     * <li>3.1 HttpRequest header</li>
     * <li>3.2 BODY </li>
     * </li>
     *
     * @return message
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public Response registerUser(UserRegistrationDto userRegistrationDto) {
        RegisterModel registerModel = RegisterModel.builder()
                .name(userRegistrationDto.getName())
                .surname(userRegistrationDto.getSurname())
                .town(userRegistrationDto.getTown())
                .plainPassword(userRegistrationDto.getPlainPassword())
                .email(userRegistrationDto.getEmail())
                .contact(userRegistrationDto.getContact())
                .username(userRegistrationDto.getUsername())
                .build();
        RegisterController registerController = new RegisterController(userService, registerModel);
        if (registerController.isUsernameOccupied()) {
            return Response
                    .notModified("Username is already in use")
                    .build();
        }
        if (!registerController.isValidRegisterModel()) {
            return Response
                    .notModified("Empty field(s) are not allowed")
                    .build();
        }
        User registeredUser = userService.register(registerModel);
        if (registeredUser != null) {
            return Response
                    .created(URI.create("http://localhost:8080/CelebrationOrg-1.0-SNAPSHOT/api/users/" + registeredUser.getId()))
                    .build();
        } else {
            return Response.notModified("Problem prilikom registracije").build();
        }
    }



    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateUser(@PathParam("id") int id, UserRegistrationDto userRegistrationDto){
        User alreadyRegisteredUser = userService.find(id);
        if(alreadyRegisteredUser != null){
            alreadyRegisteredUser.setName(userRegistrationDto.getName());
            alreadyRegisteredUser.setSurname(userRegistrationDto.getSurname());
            alreadyRegisteredUser.setContact(userRegistrationDto.getContact());
            alreadyRegisteredUser.setEmail(userRegistrationDto.getEmail());
            userService.edit(alreadyRegisteredUser);
            return Response.ok().build();
        }else{
            return Response.notModified("User ne postoji").build();
        }
    }

    /**
     * <li>http://localhost:8080/CelebrationOrg-1.0-SNAPSHOT/api/users/{id}
     * <li> DELETE request
     * @param id
     * @return response
     */
    @DELETE
    @Path("{id}")
    public Response removeUser(@PathParam("id") int id){
        User alreadyRegistereduser = userService.find(id);
        if(alreadyRegistereduser != null){
            userService.remove(alreadyRegistereduser);
            return Response.ok().build();
        }else{
            return Response.notModified().build();
        }
    }
}

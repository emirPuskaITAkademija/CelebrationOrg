package ba.organizuj.celebrationorg.api.user;

import ba.organizuj.celebrationorg.ejb.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String username;
    private String name;
    private String surname;
    private String email;
    private String contact;
    private String town;
    private String privilege;

    public static UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setEmail(user.getEmail());
        userDto.setContact(user.getContact());
        userDto.setTown(user.getTown().getName());
        userDto.setPrivilege(user.getPrivilege().getName());
        return userDto;
    }
}

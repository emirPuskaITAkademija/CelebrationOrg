package ba.organizuj.celebrationorg.api.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDto extends UserDto{
    private String plainPassword;
}

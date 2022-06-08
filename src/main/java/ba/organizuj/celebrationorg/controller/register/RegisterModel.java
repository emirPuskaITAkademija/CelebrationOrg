package ba.organizuj.celebrationorg.controller.register;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterModel {
    private String name;
    private String surname;
    private String username;
    private String plainPassword;
    private String email;
    private String town;
    private String contact;

    public Integer getTownId(){
        if(town.contains("[")){
            String townIdText = town.substring(town.indexOf("[")+1, town.indexOf("]"));
            return Integer.parseInt(townIdText);
        }else{
            return Integer.parseInt(town);
        }
    }
}

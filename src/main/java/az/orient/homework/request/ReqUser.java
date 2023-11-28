package az.orient.homework.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReqUser {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;

}

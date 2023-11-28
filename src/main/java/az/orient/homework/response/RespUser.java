package az.orient.homework.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class RespUser {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String token;
    private Integer active;
    private Date createdAt;
    private Date updatedAt;

}

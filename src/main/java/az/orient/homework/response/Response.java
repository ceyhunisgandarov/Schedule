package az.orient.homework.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {

    @JsonProperty(value = "listResponse")
    private T t;
    private ResponseStatus status;

}

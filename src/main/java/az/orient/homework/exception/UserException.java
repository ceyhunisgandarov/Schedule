package az.orient.homework.exception;

public class UserException extends RuntimeException{

    private Integer code;

    public UserException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public UserException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }

}

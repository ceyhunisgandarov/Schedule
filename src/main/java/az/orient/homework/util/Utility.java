package az.orient.homework.util;

import az.orient.homework.entity.User;
import az.orient.homework.enums.EnumStatus;
import az.orient.homework.exception.ExceptionConstant;
import az.orient.homework.exception.UserException;
import az.orient.homework.repository.UserRepository;
import az.orient.homework.response.Response;
import az.orient.homework.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Utility {

    private final UserRepository userRepository;

    public Response updateActiveToPending(Long id) {
        Response response = new Response();
        try {
            if(id == null) {
                throw new UserException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request data");
            }
            User user = userRepository.findUserById(id);
            if (user == null) {
                throw new UserException(ExceptionConstant.USER_NOT_FOUND, "User not found");
            }
            user.setActive(EnumStatus.PENDING.value);
            userRepository.save(user);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (UserException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    public Response updatePendingToSendingMail(Long id) {
        Response response = new Response();
        try {
            if(id == null) {
                throw new UserException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request data");
            }
            User user = userRepository.findUserById(id);
            if (user == null) {
                throw new UserException(ExceptionConstant.USER_NOT_FOUND, "User not found");
            }
            user.setActive(EnumStatus.SENDINGMAIL.value);
            userRepository.save(user);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (UserException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

//    public Response updateSendingMailToConfirm(Long id) {
//        Response response = new Response();
//        try {
//            if(id == null) {
//                throw new UserException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request data");
//            }
//            User user = userRepository.findUserById(id);
//            if (user == null) {
//                throw new UserException(ExceptionConstant.USER_NOT_FOUND, "User not found");
//            }
//            user.setActive(EnumStatus.CONFIRM.value);
//            userRepository.save(user);
//            response.setStatus(ResponseStatus.getSuccessMessage());
//        } catch (UserException ex) {
//            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
//        } catch (Exception ex) {
//            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal exception"));
//        }
//        return response;
//    }

    public Response confirmUser (String token) {
        System.out.println(token + " in api service method");
        Response response = new Response();
        try {
            if (token == null) {
                throw new UserException(ExceptionConstant.INVALID_TOKEN, "Invalid token");
            }
            User user = userRepository.findUserByToken(token);
            if (user == null) {
                throw new UserException(ExceptionConstant.USER_NOT_FOUND, "User not found");
            }
            System.out.println(user.getName());
            user.setActive(EnumStatus.CONFIRM.value);
            user.setToken(null);
            System.out.println("success");
            userRepository.save(user);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (UserException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    public String checkMail(String email) {
        List<User> users = userRepository.findAll();
        String checkEmail = email;
        for (User user : users) {
            if (email.equals(user.getEmail())) {
                checkEmail = null;
                break;
            }
        }
        return checkEmail;
    }
}

package az.orient.homework.service.impl;

import az.orient.homework.entity.User;
import az.orient.homework.enums.EnumStatus;
import az.orient.homework.exception.ExceptionConstant;
import az.orient.homework.exception.UserException;
import az.orient.homework.repository.UserRepository;
import az.orient.homework.request.ReqUser;
import az.orient.homework.response.RespUser;
import az.orient.homework.response.Response;
import az.orient.homework.response.ResponseStatus;
import az.orient.homework.service.UserService;
import az.orient.homework.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Utility utility;

    @Override
    public Response<List<RespUser>> getActiveUsers() {
        Response<List<RespUser>> response = new Response<>();
        try {
            List<User> activeUsers = userRepository.findAllByActive(EnumStatus.ACTIVE.value);
            if (activeUsers.isEmpty()) {
                throw new UserException(ExceptionConstant.USER_NOT_FOUND, "User not found");
            }
            List<RespUser> respUsers = activeUsers.stream().map(this::mapping).toList();
            response.setT(respUsers);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (UserException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    @Override
    public Response<List<RespUser>> getConfirmedUsers() {
        Response<List<RespUser>> response = new Response<>();
        try {
            List<User> users = userRepository.findAllByActive(EnumStatus.CONFIRM.value);
            if (users.isEmpty()) {
                throw new UserException(ExceptionConstant.USER_NOT_FOUND, "User not found");
            }
            List<RespUser> respUsers = users.stream().map(this::mapping).toList();
            response.setT(respUsers);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (UserException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    @Override
    public Response addUser(ReqUser reqUser) {
        Response response = new Response();
        try {
            String email = reqUser.getEmail();
            String password = reqUser.getPassword();
            String name = reqUser.getName();
            String surname = reqUser.getSurname();
            if (email.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty()) {
                throw new UserException(ExceptionConstant.INVALID_REQUEST_DATA, "Invalid Request Data");
            }
            String checkedEmail = utility.checkMail(email);
            String token = UUID.randomUUID().toString();
            User user = new User();
            if (checkedEmail == null) {
                throw new UserException(ExceptionConstant.INVALID_EMAIL, "Invalid email");
            } else {
                user.setEmail(email);
            }
            user.setPassword(password);
            user.setName(name);
            user.setSurname(surname);
            user.setToken(token);
            userRepository.save(user);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (UserException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    @Override
    public Response<List<RespUser>> getAll() {
        Response<List<RespUser>> response = new Response<>();
        try {
            List<User> activeUsers = userRepository.findAll();
            if (activeUsers.isEmpty()) {
                throw new UserException(ExceptionConstant.USER_NOT_FOUND, "User not found");
            }
            List<RespUser> respUsers = activeUsers.stream().map(this::mapping).toList();
            response.setT(respUsers);
            response.setStatus(ResponseStatus.getSuccessMessage());
        } catch (UserException ex) {
            response.setStatus(new ResponseStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new ResponseStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal exception"));
        }
        return response;
    }

    private RespUser mapping(User user) {

        RespUser respUser = RespUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .active(user.getActive())
                .createdAt(user.getCreatedAt())
                //.updatedAt(user.getUpdatedAt())
                .password(user.getPassword())
                .token(user.getToken())
                .build();

        return respUser;
    }
}

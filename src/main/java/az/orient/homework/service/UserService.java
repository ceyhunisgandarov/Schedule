package az.orient.homework.service;

import az.orient.homework.request.ReqUser;
import az.orient.homework.response.RespUser;
import az.orient.homework.response.Response;

import java.util.List;

public interface UserService {

    Response<List<RespUser>> getActiveUsers();

    Response<List<RespUser>> getConfirmedUsers();

    Response addUser(ReqUser reqUser);

    Response<List<RespUser>> getAll();
}

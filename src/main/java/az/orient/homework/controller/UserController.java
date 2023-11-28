package az.orient.homework.controller;

import az.orient.homework.request.ReqUser;
import az.orient.homework.response.RespUser;
import az.orient.homework.response.Response;
import az.orient.homework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/listAll")
    public Response<List<RespUser>> getAll() {
        return userService.getAll();
    }

    @GetMapping("/list")
    public Response<List<RespUser>> getList() {
        return userService.getConfirmedUsers();
    }

    @PostMapping("/save")
    public Response save(@RequestBody ReqUser reqUser) {
        return userService.addUser(reqUser);
    }

}

package az.orient.homework.controller;

import az.orient.homework.response.Response;
import az.orient.homework.service.UserService;
import az.orient.homework.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ConfirmController {

    private final Utility utility;

    @PostMapping("/confirm")
    public Response confirm(@RequestParam String token) {
        System.out.println(token + " in api controller method");
        return utility.confirmUser(token);
    }

}

package com.empik.demo.api.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService service;

    @GetMapping("/{login}")
    public String getUser(@PathVariable String login, Model model) {
        model.addAttribute("user", service.getUser(login));
        return "users";
    }

}

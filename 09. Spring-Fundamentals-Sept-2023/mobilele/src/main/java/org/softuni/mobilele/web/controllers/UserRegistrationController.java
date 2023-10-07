package org.softuni.mobilele.web.controllers;

import org.softuni.mobilele.models.dto.UserRegistrationDTO;
import org.softuni.mobilele.service.UserRegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Controller
public class UserRegistrationController {
    private final UserRegistrationService userRegistrationService;

    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }


    @PostMapping("/register")
    public String register(UserRegistrationDTO userRegistrationDTO){
        userRegistrationService.registerUser(userRegistrationDTO);
        return "redirect:/";
    }
}

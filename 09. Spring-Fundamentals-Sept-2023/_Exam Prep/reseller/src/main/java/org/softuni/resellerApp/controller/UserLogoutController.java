package org.softuni.resellerApp.controller;

import lombok.AllArgsConstructor;
import org.softuni.resellerApp.service.impl.UserLogoutServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class UserLogoutController {
    private UserLogoutServiceImpl userLogoutService;

    @GetMapping("/user/logout")
    public String logout() {
        userLogoutService.logoutUser();
        return "redirect:/";
    }
}


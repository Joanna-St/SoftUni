package org.softuni.resellerApp.controller;

import lombok.AllArgsConstructor;
import org.softuni.resellerApp.service.impl.UserLogoutServiceImpl;
import org.softuni.resellerApp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class UserLogoutController {
    private UserLogoutServiceImpl userLogoutService;
    private CurrentUser currentUser;

    @GetMapping("/user/logout")
    public String logout() {

        if (currentUser.isLogged()) {
            userLogoutService.logoutUser();
        }

        return "redirect:/";
    }
}


package org.softuni.resellerApp.controller;

import lombok.AllArgsConstructor;
import org.softuni.resellerApp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping("/")
@Controller
public class IndexController {
    private CurrentUser currentUser;

    @GetMapping("")
    public String index(Model model) {
        if (currentUser.isLogged()) {
            return "redirect:/home";
        } else {
            return "index";
        }
    }
}

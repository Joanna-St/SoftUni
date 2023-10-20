package org.softuni.resellerApp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping("/home")
@Controller
public class HomePageController {

    @GetMapping("")
    public String home(Model model) {
        return "home";
    }
}

package org.softuni.resellerApp.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.softuni.resellerApp.model.dto.UserLoginDTO;
import org.softuni.resellerApp.service.impl.UserLoginServiceImpl;
import org.softuni.resellerApp.util.Messages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class UserLoginController {
    private UserLoginServiceImpl loginService;

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("userLoginDTO")) {
            model.addAttribute("userLoginDTO",
                    new UserLoginDTO(null, null));
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("userLoginDTO", userLoginDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);

            return "redirect:/user/login";
        }

        if (loginService.loginUser(userLoginDTO)) {
            return "redirect:/home";
        } else {
            rAtt.addFlashAttribute("userLoginDTO", userLoginDTO);
            rAtt.addFlashAttribute("loginError", Messages.WRONG_CREDENTIALS);
            return "redirect:/user/login";
        }
    }
}

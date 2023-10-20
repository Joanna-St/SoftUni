package org.softuni.resellerApp.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.softuni.resellerApp.model.dto.UserRegistrationDTO;
import org.softuni.resellerApp.service.impl.UserRegistrationServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/user")
@Controller
public class UserRegistrationController {
    private final UserRegistrationServiceImpl userRegistrationService;

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegistrationDTO")) {
            model.addAttribute("userRegistrationDTO",
                    new UserRegistrationDTO(null,null,null,null));
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
        String passwordMatchError = userRegistrationDTO.validatePasswordMatch();

        if (bindingResult.hasErrors() || passwordMatchError != null) {
            if (passwordMatchError != null) {
                rAtt.addFlashAttribute("passwordMatchError", passwordMatchError);
            }

            rAtt.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);
            return "redirect:/user/register";
        }

        userRegistrationService.registerUser(userRegistrationDTO);
        return "redirect:/home";
    }
}

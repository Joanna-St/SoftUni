package org.softuni.dictionary.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.softuni.dictionary.model.dtos.UserLoginDTO;
import org.softuni.dictionary.model.dtos.UserRegistrationDTO;
import org.softuni.dictionary.service.impl.LoginServiceImpl;
import org.softuni.dictionary.service.impl.RegistrationServiceImpl;
import org.softuni.dictionary.util.CurrentUser;
import org.softuni.dictionary.util.Messages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private CurrentUser currentUser;
    private LoginServiceImpl loginService;
    private RegistrationServiceImpl registrationService;

    //    LOGIN FUNCTIONALITIES ========================================================================================
    @GetMapping("/login")
    public String login(Model model) {
        if (!currentUser.isLogged()) {
            if (!model.containsAttribute("userLoginDTO")) {
                model.addAttribute("userLoginDTO",
                        new UserLoginDTO(null, null));
            }
            return "login";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
//      LOGGED USER GETS REDIRECTED
        if (currentUser.isLogged()) {
            return "redirect:/";
        }

//      GUEST USER - VALIDATION CHECKS
        if (bindingResult.hasErrors() || !loginService.userLogin(userLoginDTO)) {
            rAtt.addFlashAttribute("userLoginDTO", userLoginDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);
            rAtt.addFlashAttribute("loginError", Messages.WRONG_CREDENTIALS);
            return "redirect:/user/login";
        }

//      LOGIN SUCCESS
        return "redirect:/";
    }

    //    REGISTER FUNCTIONALITIES =====================================================================================
    @GetMapping("/register")
    public String register(Model model) {
        if (!currentUser.isLogged()) {
            if (!model.containsAttribute("userRegistrationDTO")) {
                model.addAttribute("userRegistrationDTO",
                        new UserRegistrationDTO(null, null, null, null));
            }

            return "register";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
//      LOGGED USER GETS REDIRECTED
        if (currentUser.isLogged()) {
            return "redirect:/";
        }

//      GUEST USER - VALIDATION CHECKS
//      Pass match
        String passwordMatchError = userRegistrationDTO.validatePasswordMatch();
//      Username/Email match
        Map<String, String> registerUser = registrationService.registerUser(userRegistrationDTO);

        if (bindingResult.hasErrors() || passwordMatchError != null || !registerUser.isEmpty()) {
            if (passwordMatchError != null) {
                rAtt.addFlashAttribute("passwordMatchError", passwordMatchError);
            }

            if (!registerUser.isEmpty()) {
                rAtt.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
                registerUser.forEach(rAtt::addFlashAttribute);
            }

            rAtt.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);
            return "redirect:/user/register";
        }

//      DTO CORRECT
        return "redirect:/";

    }

    //    LOGOUT FUNCTIONALITY =========================================================================================
    @GetMapping("/logout")
    public String logout() {
        if (currentUser.isLogged()) {
            loginService.userLogout();
        }

        return "redirect:/";
    }
}

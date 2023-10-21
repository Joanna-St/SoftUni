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
        if (!currentUser.isLogged()) {

//          VALIDATIONS CHECK
            if (bindingResult.hasErrors()) {
                rAtt.addFlashAttribute("userLoginDTO", userLoginDTO);
                rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);

                return "redirect:/user/login";
            }

            if (loginService.userLogin(userLoginDTO)) {
//              SUCCESS
                return "redirect:/home";
            } else {

//              PASS ON INFO THAT THE LOGIN WAS UNSUCCESSFUL
                rAtt.addFlashAttribute("userLoginDTO", userLoginDTO);
                rAtt.addFlashAttribute("loginError", Messages.WRONG_CREDENTIALS);
                return "redirect:/user/login";
            }
        } else {
            return "redirect:/";
        }
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

        if (!currentUser.isLogged()) {
            String passwordMatchError = userRegistrationDTO.validatePasswordMatch();

//          VALIDATIONS CHECK
            if (bindingResult.hasErrors() || passwordMatchError != null) {

//              MATCHING PASSWORDS CHECK - RETURN ERROR MESSAGE IF THEY DON'T
                if (passwordMatchError != null) {
                    rAtt.addFlashAttribute("passwordMatchError", passwordMatchError);
                }

                rAtt.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
                rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);
                return "redirect:/user/register";
            }

            registrationService.registerUser(userRegistrationDTO);
            return "redirect:/home";
        } else {
            return "redirect:/";
        }
    }

    //    LOGOUT FUNCTIONALITY =========================================================================================
    @GetMapping("/user/logout")
    public String logout() {

        if (currentUser.isLogged()) {
            loginService.userLogout();
        }

        return "redirect:/";
    }
}

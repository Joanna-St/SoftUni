package org.softuni.resellerApp.controller;

import lombok.AllArgsConstructor;
import org.softuni.resellerApp.model.entity.Offer;
import org.softuni.resellerApp.model.entity.User;
import org.softuni.resellerApp.service.UserService;
import org.softuni.resellerApp.service.impl.OfferServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/home")
@Controller
public class HomePageController {
    private OfferServiceImpl offerService;
    private UserService userService;

    @ModelAttribute("allUsers")
    public List<User> allUsers() {
        return userService.getAllUsersWithOffers();
    }

    @ModelAttribute("allOffers")
    public List<Offer> allOffers() {
        return offerService.getAllOtherOffers();
    }

    @ModelAttribute("userOffers")
    public List<Offer> userOffers() {
        return offerService.getUserOffers();
    }

    @ModelAttribute("userBoughtOffers")
    public List<Offer> userBoughtOffers() {
        return offerService.getUserBoughtOffers();
    }

    @GetMapping("")
    public String home(Model model) {
        return "home";
    }
}
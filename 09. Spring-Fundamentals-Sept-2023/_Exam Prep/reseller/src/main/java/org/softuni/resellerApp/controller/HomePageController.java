package org.softuni.resellerApp.controller;

import lombok.AllArgsConstructor;
import org.softuni.resellerApp.model.dto.OfferDTO;
import org.softuni.resellerApp.service.impl.OfferServiceImpl;
import org.softuni.resellerApp.util.CurrentUser;
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

    @ModelAttribute("allOffers")
    public List<OfferDTO> allOffers(){
        return offerService.getAllOtherOffers();
    }

    @ModelAttribute("userOffers")
    public List<OfferDTO> userOffers(){
        return offerService.getUserOffers();
    }

    @ModelAttribute("userBoughtOffers")
    public List<OfferDTO> userBoughtOffers(){
        return offerService.getUserBoughtOffers();
    }

    @GetMapping("")
    public String home(Model model) {
        return "home";
    }
}
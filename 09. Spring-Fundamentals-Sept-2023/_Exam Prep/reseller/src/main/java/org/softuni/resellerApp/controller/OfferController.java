package org.softuni.resellerApp.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.softuni.resellerApp.model.dto.OfferDTO;
import org.softuni.resellerApp.model.enums.ConditionEnum;
import org.softuni.resellerApp.service.impl.OfferServiceImpl;
import org.softuni.resellerApp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@RequestMapping("/offer")
@Controller
public class OfferController {
    private OfferServiceImpl offerService;
    private CurrentUser currentUser;

    @ModelAttribute("conditions")
    public ConditionEnum[] conditions() {
        return ConditionEnum.values();
    }

    @GetMapping("/add")
    public String addOffer(Model model) {
        if (currentUser.isLogged()) {
            if (!model.containsAttribute("offerDTO")) {
                model.addAttribute("offerDTO",
                        new OfferDTO(null, null, null, null));
            }

            return "offer-add";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/add")
    public String addOffer(@Valid OfferDTO offerDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
        if (currentUser.isLogged()) {
            if (bindingResult.hasErrors()) {
                rAtt.addFlashAttribute("offerDTO", offerDTO);
                rAtt.addFlashAttribute("org.springframework.validation.BindingResult.offerDTO", bindingResult);
                return "redirect:/offer/add";
            }
            offerService.addOffer(offerDTO);
            return "redirect:/home";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/buy/{id}")
    public String buyOffer(@PathVariable("id") Long id) {
        if (currentUser.isLogged()) {
            offerService.buyOffer(id);
            return "redirect:/home";
        } else {
            return "redirect:/";
        }
    }

    @DeleteMapping("/remove/{id}")
    public String deleteOffer(@PathVariable("id") Long id) {
        if (currentUser.isLogged()) {
            offerService.deleteOffer(id);
            return "redirect:/home";
        } else {
            return "redirect:/";
        }
    }
}

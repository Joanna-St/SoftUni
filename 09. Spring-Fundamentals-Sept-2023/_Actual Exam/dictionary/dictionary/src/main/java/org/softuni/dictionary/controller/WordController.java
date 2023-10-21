package org.softuni.dictionary.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.softuni.dictionary.model.dtos.WordDTO;
import org.softuni.dictionary.model.enums.LanguageEnum;
import org.softuni.dictionary.service.impl.WordServiceImpl;
import org.softuni.dictionary.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@RequestMapping("/words")
@Controller
public class WordController {
    private WordServiceImpl wordService;
    private CurrentUser currentUser;

    @ModelAttribute("allLanguages")
    private LanguageEnum[] allLanguages(){
        return LanguageEnum.values();
    }

    //    ADD WORD FUNCTIONALITY =======================================================================================
    @GetMapping("/add")
    public String addOffer(Model model) {
        if (currentUser.isLogged()) {
            if (!model.containsAttribute("wordDTO")) {
                model.addAttribute("wordDTO",
                        new WordDTO(null, null, null, null, null));
            }

            return "word-add";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/add")
    public String addOffer(@Valid WordDTO wordDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
        if (currentUser.isLogged()) {
            if (bindingResult.hasErrors()) {
                rAtt.addFlashAttribute("wordDTO", wordDTO);
                rAtt.addFlashAttribute("org.springframework.validation.BindingResult.wordDTO", bindingResult);
                return "redirect:/words/add";
            }
            wordService.addWord(wordDTO);
        }
        return "redirect:/";
    }

    //    REMOVE ONE WORD FUNCTIONALITY ================================================================================
    @DeleteMapping("/remove/{id}")
    public String deleteWord(@PathVariable("id") Long id) {
        if (currentUser.isLogged()) {
            wordService.deleteWord(id);
        }
        return "redirect:/";
    }

    //    REMOVE ALL WORDS FUNCTIONALITY ===============================================================================
    @DeleteMapping("/remove-all")
    public String deleteAllWords() {
        if (currentUser.isLogged()) {
            wordService.deleteAllWords();
        }
        return "redirect:/";
    }
}

package org.softuni.dictionary.controller;

import lombok.AllArgsConstructor;
import org.softuni.dictionary.model.entity.Word;
import org.softuni.dictionary.service.impl.WordServiceImpl;
import org.softuni.dictionary.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/")
@Controller
public class HomePageController {
    private CurrentUser currentUser;
    private WordServiceImpl wordService;

//  GET LISTS PER LANGUAGE
    @ModelAttribute("germanWords")
    public List<Word> germanWords(){
        return wordService.getWordRepository().getAllGermanWords();
    }

    @ModelAttribute("spanishWords")
    public List<Word> spanishWords(){
        return wordService.getWordRepository().getAllSpanishWords();
    }

    @ModelAttribute("frenchWords")
    public List<Word> frenchWords(){
        return wordService.getWordRepository().getAllFrenchWords();
    }

    @ModelAttribute("italianWords")
    public List<Word> italianWords(){
        return wordService.getWordRepository().getAllItalianWords();
    }


    @ModelAttribute("allWordsCount")
    public Long allWordsCount(){
        return wordService.getWordRepository().count();
    }

//  INDEX FUNCTIONALITY - USER LOGGED IN OR OUT ========================================================================
    @GetMapping("")
    public String index(Model model) {
        if (currentUser.isLogged()) {
            return "home";
        } else {
            return "index";
        }
    }
}

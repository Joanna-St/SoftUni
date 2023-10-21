package org.softuni.dictionary.service.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.softuni.dictionary.model.dtos.WordDTO;
import org.softuni.dictionary.model.entity.Word;
import org.softuni.dictionary.model.enums.LanguageEnum;
import org.softuni.dictionary.repo.LanguageRepository;
import org.softuni.dictionary.repo.UserRepository;
import org.softuni.dictionary.repo.WordRepository;
import org.softuni.dictionary.service.WordService;
import org.softuni.dictionary.util.CurrentUser;
import org.springframework.stereotype.Service;
@Getter
@AllArgsConstructor
@Service
public class WordServiceImpl implements WordService {
    private WordRepository wordRepository;
    private UserRepository userRepository;
    private LanguageRepository languageRepository;
    private CurrentUser currentUser;

    @Override
    public void addWord(WordDTO wordDTO) {
        wordRepository.save(map(wordDTO));
    }

    @Override
    public void deleteWord(Long id) {
        wordRepository.deleteById(id);
    }

    @Override
    public void deleteAllWords() {
        wordRepository.deleteAll();
    }

    private Word map(WordDTO wordDTO) {
        Word newWord = new Word();

        newWord.setTerm(wordDTO.term());
        newWord.setTranslation(wordDTO.translation());
        newWord.setExample(wordDTO.example());
        newWord.setInputDate(wordDTO.inputDate());
        newWord.setLanguage(languageRepository.getLanguageByLanguageName(LanguageEnum.valueOf(wordDTO.language())));
        newWord.setAddedBy(userRepository.getUserById(currentUser.getId()));

        return newWord;
    }
}

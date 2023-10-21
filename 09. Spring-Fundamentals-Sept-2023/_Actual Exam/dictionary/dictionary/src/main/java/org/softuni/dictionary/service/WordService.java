package org.softuni.dictionary.service;

import org.softuni.dictionary.model.dtos.WordDTO;

public interface WordService {
    void addWord(WordDTO wordDTO);

    void deleteWord(Long id);

    void deleteAllWords();
}

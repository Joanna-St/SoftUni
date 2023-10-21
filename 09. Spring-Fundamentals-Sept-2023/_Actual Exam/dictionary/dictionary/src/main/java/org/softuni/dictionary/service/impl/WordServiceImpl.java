package org.softuni.dictionary.service.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.softuni.dictionary.repo.WordRepository;
import org.softuni.dictionary.service.WordService;
import org.springframework.stereotype.Service;
@Getter
@AllArgsConstructor
@Service
public class WordServiceImpl implements WordService {
    private WordRepository wordRepository;
}

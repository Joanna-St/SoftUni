package org.softuni.dictionary.service.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.softuni.dictionary.repo.LanguageRepository;
import org.softuni.dictionary.service.LanguageService;
import org.springframework.stereotype.Service;
@Getter
@AllArgsConstructor
@Service
public class LanguageServiceImpl implements LanguageService {
    private LanguageRepository languageRepository;
}

package org.softuni.dictionary.init;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.softuni.dictionary.model.entity.Language;
import org.softuni.dictionary.model.enums.LanguageEnum;
import org.softuni.dictionary.repo.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
public class LanguageInit implements CommandLineRunner {
    private final LanguageRepository languageRepository;
    @Override
    public void run(String... args) throws Exception {
        if (languageRepository.count() == 0) {
            for (LanguageEnum languageEnum : LanguageEnum.values()) {
                Language language = new Language(languageEnum);
                languageRepository.save(language);
            }
        }
    }
}

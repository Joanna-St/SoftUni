package org.softuni.dictionary.repo;

import org.softuni.dictionary.model.entity.Language;
import org.softuni.dictionary.model.entity.Word;
import org.softuni.dictionary.model.enums.LanguageEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Language getLanguageByLanguageName(LanguageEnum languageName);
}

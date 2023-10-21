package org.softuni.dictionary.repo;

import org.softuni.dictionary.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    @Query(value = "SELECT w FROM " +
            "Word w " +
            "WHERE w.language.languageName = 'GERMAN'")
    List<Word> getAllGermanWords();

    @Query(value = "SELECT w FROM " +
            "Word w " +
            "WHERE w.language.languageName = 'SPANISH'")
    List<Word> getAllSpanishWords();

    @Query(value = "SELECT w FROM " +
            "Word w " +
            "WHERE w.language.languageName = 'FRENCH'")
    List<Word> getAllFrenchWords();

    @Query(value = "SELECT w FROM " +
            "Word w " +
            "WHERE w.language.languageName = 'ITALIAN'")
    List<Word> getAllItalianWords();
}

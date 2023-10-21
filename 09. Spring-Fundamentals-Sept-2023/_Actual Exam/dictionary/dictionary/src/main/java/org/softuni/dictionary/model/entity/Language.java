package org.softuni.dictionary.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.dictionary.model.enums.LanguageEnum;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "languages")
public class Language extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, name = "language_name")
    private LanguageEnum languageName;

    @Column(nullable = false)
    private String description;

    @OneToMany(targetEntity = Word.class, mappedBy = "language")
    private List<Word> words;

    public Language(LanguageEnum languageName) {
        this.languageName = languageName;
        this.description = languageName.getDescription();
    }
}

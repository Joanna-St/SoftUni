package org.softuni.dictionary.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "words")
public class Word extends BaseEntity{
    @Column(nullable = false)
    private String term;

    @Column(nullable = false)
    private String translation;

    @Column(nullable = false)
    private String example;

    @Column(nullable = false, name = "input_date")
    private LocalDateTime inputDate;

    @ManyToOne
    private Language language;

    @ManyToOne
    private User addedBy;

}

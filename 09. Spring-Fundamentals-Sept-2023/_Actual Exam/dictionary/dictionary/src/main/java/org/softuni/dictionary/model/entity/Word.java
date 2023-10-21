package org.softuni.dictionary.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "words")
public class Word extends BaseEntity{
    @NotBlank
    @Size(min = 2, max = 40)
    @Column(nullable = false)
    private String term;

    @NotBlank
    @Size(min = 2, max = 80)
    @Column(nullable = false)
    private String translation;

    @NotBlank
    @Size(min = 2, max = 200)
    @Column(nullable = false)
    private String example;

    @NotNull
    @PastOrPresent
    @Column(nullable = false, name = "input_date")
    private LocalDateTime inputDate;

    @ManyToOne
    private Language language;

    @ManyToOne
    private User addedBy;

}

package org.softuni.dictionary.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @NotBlank
    @Size(min = 3, max = 20)
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(unique = true, nullable = false)
    private String password;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(targetEntity = Word.class, mappedBy = "addedBy")
    private List<Word> addedWords;
}

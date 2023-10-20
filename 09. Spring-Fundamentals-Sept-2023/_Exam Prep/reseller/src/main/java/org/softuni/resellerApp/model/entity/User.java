package org.softuni.resellerApp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
    @Column(nullable = false)
    private String password;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(targetEntity = Offer.class, mappedBy = "created", fetch = FetchType.EAGER)
    private List<Offer> offers;

    @OneToMany(targetEntity = Offer.class, mappedBy = "bought", fetch = FetchType.EAGER)
    private List<Offer> boughtOffers;

    public User() {
        this.offers = new ArrayList<>();
        this.boughtOffers = new ArrayList<>();
    }
}

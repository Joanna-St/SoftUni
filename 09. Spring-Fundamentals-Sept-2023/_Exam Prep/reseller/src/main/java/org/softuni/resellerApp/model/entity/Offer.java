package org.softuni.resellerApp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {
    @NotBlank
    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String description;
    @Positive
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    @ManyToOne
    private Condition condition;

    @NotNull
    @ManyToOne
    private User created;

    @ManyToOne
    private User bought;
}

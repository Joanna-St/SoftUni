package org.softuni.resellerApp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.softuni.resellerApp.model.entity.User;
import org.softuni.resellerApp.util.Messages;

import java.math.BigDecimal;

public record OfferDTO(@NotBlank(message = Messages.BLANK_DESCRIPTION)
                       @Size(min = 2, max = 50, message = Messages.LENGTH_DESCRIPTION)
                       String description,
                       @NotNull(message = Messages.BLANK_PRICE)
                       @Positive(message = Messages.POSITIVE_PRICE)
                       BigDecimal price,
                       @NotBlank(message = Messages.BLANK_CONDITION)
                       String condition,
                       User created) {
}

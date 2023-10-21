package org.softuni.dictionary.model.dtos;

import jakarta.validation.constraints.Size;
import org.softuni.dictionary.util.Messages;

public record UserLoginDTO(@Size(min = 3, max = 20, message = Messages.LENGTH_USERNAME)
                           String username,
                           @Size(min = 3, max = 20, message = Messages.LENGTH_PASSWORD)
                           String password) {
}


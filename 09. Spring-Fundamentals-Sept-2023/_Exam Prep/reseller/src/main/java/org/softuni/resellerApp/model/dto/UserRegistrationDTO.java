package org.softuni.resellerApp.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.softuni.resellerApp.util.Messages;

public record UserRegistrationDTO(@NotBlank(message = Messages.BLANK_USERNAME)
                                  @Size(min = 3, max = 20, message = Messages.LENGTH_USERNAME)
                                  String username,
                                  @NotBlank(message = Messages.BLANK_EMAIL)
                                  @Email(message = Messages.INVALID_EMAIL)
                                  String email,
                                  @NotBlank(message = Messages.BLANK_PASSWORD)
                                  @Size(min = 3, max = 20, message = Messages.LENGTH_PASSWORD)
                                  String password,
                                  @NotBlank(message = Messages.BLANK_PASSWORD)
                                  @Size(min = 3, max = 20, message = Messages.LENGTH_PASSWORD)
                                  String confirmPassword) {

    public String validatePasswordMatch() {
        if (!password.equals(confirmPassword)) {
            return "Passwords do not match.";
        }
        return null; // No error
    }
}

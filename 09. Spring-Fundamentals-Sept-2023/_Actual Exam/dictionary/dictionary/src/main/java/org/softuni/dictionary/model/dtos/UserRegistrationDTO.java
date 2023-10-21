package org.softuni.dictionary.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.softuni.dictionary.util.Messages;

public record UserRegistrationDTO(@Size(min = 3, max = 20, message = Messages.LENGTH_USERNAME)
                                  String username,
                                  @NotBlank(message = Messages.BLANK_EMAIL)
                                  @Email(message = Messages.INVALID_EMAIL)
                                  String email,
                                  @Size(min = 3, max = 20, message = Messages.LENGTH_PASSWORD)
                                  String password,
                                  @Size(min = 3, max = 20, message = Messages.LENGTH_PASSWORD)
                                  String confirmPassword) {


//  MATCHING PASSWORDS CHECK
    public String validatePasswordMatch() {
        if (!password.equals(confirmPassword)) {
            return Messages.PASSWORD_MISMATCH;
        }
        return null;
    }
}

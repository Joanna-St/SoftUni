package org.softuni.dictionary.service;

import org.softuni.dictionary.model.dtos.UserRegistrationDTO;

public interface RegistrationService {
    void registerUser(UserRegistrationDTO userRegistrationDTO);
}

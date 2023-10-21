package org.softuni.dictionary.service;

import org.softuni.dictionary.model.dtos.UserRegistrationDTO;

import java.util.Map;

public interface RegistrationService {
    Map<String, String> registerUser(UserRegistrationDTO userRegistrationDTO);
}

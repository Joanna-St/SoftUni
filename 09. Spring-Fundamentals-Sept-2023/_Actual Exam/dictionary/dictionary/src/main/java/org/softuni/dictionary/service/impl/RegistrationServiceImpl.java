package org.softuni.dictionary.service.impl;

import lombok.AllArgsConstructor;
import org.softuni.dictionary.model.dtos.UserRegistrationDTO;
import org.softuni.dictionary.model.entity.User;
import org.softuni.dictionary.repo.UserRepository;
import org.softuni.dictionary.service.RegistrationService;
import org.softuni.dictionary.util.CurrentUser;
import org.softuni.dictionary.util.Messages;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    @Override
    public Map<String, String> registerUser(UserRegistrationDTO userRegistrationDTO) {
        Map<String, String> usernameEmailValidation = new HashMap<>();
        if (userRepository.findByUsername(userRegistrationDTO.username()).isPresent()){
            usernameEmailValidation.put("usernameMatchError", Messages.USERNAME_EXISTS);
        }

        if (userRepository.findByEmail(userRegistrationDTO.email()).isPresent()){
            usernameEmailValidation.put("emailMatchError", Messages.EMAIL_EXISTS);
        }

        if (usernameEmailValidation.isEmpty()){
            userRepository.save(map(userRegistrationDTO));
        }

        return usernameEmailValidation;
    }

    private User map(UserRegistrationDTO userRegistrationDTO) {
        User newUser = new User();
        newUser.setUsername(userRegistrationDTO.username());
        newUser.setPassword(passwordEncoder.encode(userRegistrationDTO.password()));
        newUser.setEmail(userRegistrationDTO.email());
        newUser.setAddedWords(new ArrayList<>());

        return newUser;
    }
}

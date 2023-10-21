package org.softuni.dictionary.service.impl;

import lombok.AllArgsConstructor;
import org.softuni.dictionary.model.dtos.UserRegistrationDTO;
import org.softuni.dictionary.model.entity.User;
import org.softuni.dictionary.repo.UserRepository;
import org.softuni.dictionary.service.RegistrationService;
import org.softuni.dictionary.util.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        currentUser.login(userRepository.save(map(userRegistrationDTO)));
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

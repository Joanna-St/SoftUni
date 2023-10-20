package org.softuni.resellerApp.service.impl;

import lombok.AllArgsConstructor;
import org.softuni.resellerApp.model.dto.UserRegistrationDTO;
import org.softuni.resellerApp.model.entity.User;
import org.softuni.resellerApp.repository.UserRepository;
import org.softuni.resellerApp.service.UserRegistrationService;
import org.softuni.resellerApp.util.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
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
        newUser.setOffers(new ArrayList<>());
        newUser.setBoughtOffers(new ArrayList<>());

        return newUser;
    }
}

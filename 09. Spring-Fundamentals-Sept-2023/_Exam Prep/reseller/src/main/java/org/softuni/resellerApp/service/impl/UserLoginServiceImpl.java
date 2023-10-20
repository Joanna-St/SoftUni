package org.softuni.resellerApp.service.impl;

import lombok.AllArgsConstructor;
import org.softuni.resellerApp.model.dto.UserLoginDTO;
import org.softuni.resellerApp.model.entity.User;
import org.softuni.resellerApp.repository.UserRepository;
import org.softuni.resellerApp.service.UserLoginService;
import org.softuni.resellerApp.util.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserLoginServiceImpl implements UserLoginService {
    private UserRepository userRepository;
    private CurrentUser currentUser;
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean loginUser(UserLoginDTO userLoginDTO) {
        Optional<User> findUser = userRepository.findByUsername(userLoginDTO.username());

        if (findUser.isPresent()) {
            String encodedPassword = findUser.get().getPassword();
            String rawPassword = userLoginDTO.password();

            if (passwordEncoder.matches(rawPassword, encodedPassword)) {
                currentUser.login(findUser.get());
                return true;
            }
        }

        return false;
    }
}

package org.softuni.dictionary.service.impl;

import lombok.AllArgsConstructor;
import org.softuni.dictionary.model.dtos.UserLoginDTO;
import org.softuni.dictionary.model.entity.User;
import org.softuni.dictionary.repo.UserRepository;
import org.softuni.dictionary.service.LoginService;
import org.softuni.dictionary.util.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {
    private UserRepository userRepository;
    private CurrentUser currentUser;
    private PasswordEncoder passwordEncoder;

//  LOGIN
    @Override
    public boolean userLogin(UserLoginDTO loginDTO) {
        Optional<User> findUser = userRepository.findByUsername(loginDTO.username());

        if (findUser.isPresent()) {
            String encodedPassword = findUser.get().getPassword();
            String rawPassword = loginDTO.password();

            if (passwordEncoder.matches(rawPassword, encodedPassword)) {
                currentUser.login(findUser.get());
                return true;
            }
        }

        return false;
    }

//  LOGOUT
    @Override
    public void userLogout() {
        currentUser.logout();
    }
}

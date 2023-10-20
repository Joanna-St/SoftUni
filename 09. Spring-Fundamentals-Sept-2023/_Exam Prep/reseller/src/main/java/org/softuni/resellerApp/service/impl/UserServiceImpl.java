package org.softuni.resellerApp.service.impl;

import lombok.AllArgsConstructor;
import org.softuni.resellerApp.model.entity.User;
import org.softuni.resellerApp.repository.UserRepository;
import org.softuni.resellerApp.service.UserService;
import org.softuni.resellerApp.util.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private CurrentUser currentUser;

    @Override
    public List<User> getAllUsersWithOffers() {
        return userRepository.findAllOtherUsersWithOffers(currentUser.getId());
    }
}

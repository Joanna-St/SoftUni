package org.softuni.resellerApp.service.impl;

import lombok.AllArgsConstructor;
import org.softuni.resellerApp.service.UserLogoutService;
import org.softuni.resellerApp.util.CurrentUser;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserLogoutServiceImpl implements UserLogoutService {
    private final CurrentUser currentUser;

    @Override
    public void logoutUser() {
        currentUser.logout();
    }
}

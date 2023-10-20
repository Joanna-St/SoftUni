package org.softuni.resellerApp.service;

import org.softuni.resellerApp.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsersWithOffers();
}

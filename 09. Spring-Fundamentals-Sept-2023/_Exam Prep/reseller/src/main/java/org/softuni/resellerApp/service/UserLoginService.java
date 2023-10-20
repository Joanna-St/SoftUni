package org.softuni.resellerApp.service;

import org.softuni.resellerApp.model.dto.UserLoginDTO;

public interface UserLoginService {
    boolean loginUser(UserLoginDTO userLoginDTO);
}

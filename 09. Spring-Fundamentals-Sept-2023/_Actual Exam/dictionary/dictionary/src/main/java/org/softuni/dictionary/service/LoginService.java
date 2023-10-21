package org.softuni.dictionary.service;

import org.softuni.dictionary.model.dtos.UserLoginDTO;

public interface LoginService {
    boolean userLogin(UserLoginDTO loginDTO);

    void userLogout();
}

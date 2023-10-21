package org.softuni.dictionary.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.dictionary.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@NoArgsConstructor
@Component("currentUser")
@SessionScope
public class CurrentUser {
    private Long id;
    private String username;
    private boolean isLogged;

    public void logout() {
        setId(null);
        setUsername(null);
        setLogged(false);
    }

    public void login(User user) {
        setId(user.getId());
        setUsername(user.getUsername());
        setLogged(true);
    }
}
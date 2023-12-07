package org.but.feec.bds.services;

import org.but.feec.bds.api.UserAuthView;
import org.but.feec.bds.data.UserRepository;
import org.but.feec.bds.exceptions.ResourceNotFoundException;

import static org.but.feec.bds.services.Argon2FactoryService.ARGON2;

public class AuthService {
    private UserRepository userRepository;

    public  AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserAuthView findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public boolean authenticate(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }
        UserAuthView userAuthView = findUserByUsername(username);
        if (userAuthView == null) {
            throw new ResourceNotFoundException("Provided username was not found.");
        }
        return ARGON2.verify(userAuthView.getPassword(), password.toCharArray());
    }
}

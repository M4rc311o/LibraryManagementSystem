package org.but.feec.bds.services;

import org.but.feec.bds.api.UserSessionView;
import org.but.feec.bds.data.UserRepository;
import org.but.feec.bds.data.UserSession;

public class SessionService {
    private UserSession userSession;
    private UserRepository userRepository;

    public SessionService(UserRepository userRepository, UserSession userSession) {
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public long getCurrentUserId() {
        return userSession.getUserId();
    }

    public String getCurrentRole() {
        return userSession.getRole();
    }

    public String getCurrentUsername() {
        return userSession.getUsername();
    }

    public void saveSession(String username) {
        UserSessionView userSessionView = findUserByUsername(username);
        userSession.setUserId(userSessionView.getUserId());
        userSession.setRole(userSessionView.getRole());
        userSession.setUsername(userSessionView.getUsername());
    }

    private UserSessionView findUserByUsername(String username) {
        return userRepository.findUserSessionViewByUsername(username);
    }
}

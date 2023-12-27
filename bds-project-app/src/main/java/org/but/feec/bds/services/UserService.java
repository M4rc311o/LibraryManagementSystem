package org.but.feec.bds.services;

import org.but.feec.bds.api.*;
import org.but.feec.bds.data.UserRepository;
import org.but.feec.bds.exceptions.ResourceNotFoundException;
import org.but.feec.bds.exceptions.StructureViolationException;

import static org.but.feec.bds.services.Argon2FactoryService.ARGON2;

import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserSimpleView> getStandardUsersSimpleView() {
        return userRepository.getStandardUsersSimpleView();
    }

    public UserDeatiledView getUserDetailedViewById(Long id) {
        UserDeatiledView userDeatiledView = userRepository.getUserDetailedViewById(id);
        if (userDeatiledView == null) {
            throw new ResourceNotFoundException("Provided id was not found.");
        }
        return userDeatiledView;
    }

    public void createUser(UserCreateView userCreateView) throws StructureViolationException {
        if (usernameAlreadyExists(userCreateView.getUsername())) {
            throw new StructureViolationException("This username is already used.");
        }
        userCreateView.setPassword(hashPassword(userCreateView.getPassword()));
        userRepository.createUser(userCreateView);
    }

    public void editStandardUser(StandardUserEditView standardUserEditView) {
        userRepository.editStandardUser(standardUserEditView);
    }

    public void editUser(UserEditView userEditView) {
        if (userEditView.getPassword() != null && userEditView.getPassword().length != 0) {
            userEditView.setPassword(hashPassword(userEditView.getPassword()));
        }
        else {
            userEditView.setPassword(null);
        }
        userRepository.editUser(userEditView);
    }

    public boolean usernameAlreadyExists(String username) {
        return userRepository.findUserSessionViewByUsername(username) != null;
    }

    public void deleteUser(Long id){
        userRepository.deleteUser(id);
    }

    public char[] hashPassword(char[] password) {
        return ARGON2.hash(10, 65536, 1, password).toCharArray();
    }
}

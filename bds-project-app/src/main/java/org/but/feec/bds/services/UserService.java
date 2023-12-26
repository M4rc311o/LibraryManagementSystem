package org.but.feec.bds.services;

import org.but.feec.bds.api.StandardUserDetailedView;
import org.but.feec.bds.api.StandardUserEditView;
import org.but.feec.bds.api.StandardUserSimpleView;
import org.but.feec.bds.data.UserRepository;
import org.but.feec.bds.exceptions.ResourceNotFoundException;

import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<StandardUserSimpleView> getStandardUsersView() {
        return userRepository.getStandardUsersView();
    }

    public StandardUserDetailedView getStandardUserDetailedViewById(Long id) {
        StandardUserDetailedView standardUserDetailedView = userRepository.getStandardUserDetailedViewById(id);
        if (standardUserDetailedView == null) {
            throw new ResourceNotFoundException("Provided id was not found.");
        }
        return standardUserDetailedView;
    }

    public void editStandardUser(StandardUserEditView standardUserEditView) {
        userRepository.editStandardUser(standardUserEditView);
    }
}

package org.but.feec.bds.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.but.feec.bds.api.UserEditView;
import org.but.feec.bds.api.UserDeatiledView;
import org.but.feec.bds.data.UserRepository;
import org.but.feec.bds.data.UserSession;
import org.but.feec.bds.services.SessionService;
import org.but.feec.bds.services.UserService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class UserAccountEditController {
    private static final Logger logger = LoggerFactory.getLogger(UserAccountEditController.class);

    @FXML
    private Label titleLabel;
    @FXML
    private Label idValueLabel;
    @FXML
    private Label roleValueLabel;
    @FXML
    private Label usernameValueLabel;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField newPasswordPasswordField;
    @FXML
    private Button deleteAccountButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button applyButton;

    private UserRepository userRepository;
    private UserService userService;
    private SessionService sessionService;
    private ValidationSupport validationAccountEdit;

    @FXML
    private void initialize() {
        initializeServices();
        initializeValidations();

        loadUserAccountData();

        logger.info("UserAccountEditController initialized");
    }

    private void initializeServices() {
        userRepository = new UserRepository();

        userService = new UserService(userRepository);
        sessionService = new SessionService(userRepository, UserSession.getSession());
    }

    private void initializeValidations() {
        validationAccountEdit = new ValidationSupport();
        validationAccountEdit.registerValidator(firstNameTextField, Validator.createEmptyValidator("The first name must not be empty."));
        validationAccountEdit.registerValidator(lastNameTextField, Validator.createEmptyValidator("The last name must not be empty."));
        validationAccountEdit.registerValidator(phoneNumberTextField, Validator.createEmptyValidator("The phone number must not be empty."));
        //validationAccountEdit.registerValidator(emailTextField, Validator.createRegexValidator("Invalid email format", "^$|^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", Severity.ERROR));
        applyButton.disableProperty().bind(validationAccountEdit.invalidProperty());
    }

    private void loadUserAccountData() {
        UserDeatiledView userDeatiledView = userService.getUserDetailedViewById(sessionService.getCurrentUserId());
        idValueLabel.setText(String.valueOf(userDeatiledView.getId()));
        roleValueLabel.setText(userDeatiledView.getRole());
        usernameValueLabel.setText(userDeatiledView.getUsername());
        firstNameTextField.setText(userDeatiledView.getFirstName());
        lastNameTextField.setText(userDeatiledView.getLastName());
        phoneNumberTextField.setText(userDeatiledView.getPhoneNumber());
        emailTextField.setText(userDeatiledView.getEmail());
    }

    @FXML
    public void deleteAccountActionHandler(ActionEvent event) {
        handleDeleteAccount();
    }

    private void handleDeleteAccount() {
        Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION);
        alertDialog.setTitle("Delete user account");
        alertDialog.setHeaderText("You are about to delete your account");
        alertDialog.setContentText("Click OK to proceed");
        Optional<ButtonType> result = alertDialog.showAndWait();

        if (result.get() == ButtonType.OK) {
            userService.deleteUser(sessionService.getCurrentUserId());
            Stage stage = (Stage) deleteAccountButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void applyActionHandler(ActionEvent event) {
        handleApply();
    }

    private void handleApply() {
        UserEditView userEditView = new UserEditView();
        userEditView.setId(Long.valueOf(idValueLabel.getText()));
        userEditView.setFistName(firstNameTextField.getText());
        userEditView.setLastName(lastNameTextField.getText());
        userEditView.setPhoneNumber(phoneNumberTextField.getText());
        userEditView.setEmail(emailTextField.getText());
        userEditView.setPassword(newPasswordPasswordField.getText().toCharArray());

        userService.editUser(userEditView);

        Stage stage = (Stage) applyButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancelActionHandler(ActionEvent event) {
        handleCancel();
    }

    private void handleCancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}

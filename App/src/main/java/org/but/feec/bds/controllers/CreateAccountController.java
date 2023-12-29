package org.but.feec.bds.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.but.feec.bds.api.UserCreateView;
import org.but.feec.bds.data.UserRepository;
import org.but.feec.bds.exceptions.StructureViolationException;
import org.but.feec.bds.services.UserService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateAccountController {
    private static final Logger logger = LoggerFactory.getLogger(CreateAccountController.class);

    @FXML
    private Label titleLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private DatePicker dateOfBirthDatePicker;
    @FXML
    private ChoiceBox<String> roleChoiceBox;
    @FXML
    private Button cancelButton;
    @FXML
    private Button createButton;

    private UserRepository userRepository;
    private UserService userService;

    private ValidationSupport validationCreateAccount;

    @FXML
    private void initialize() {
        initializeServices();

        roleChoiceBox.getItems().addAll("basic", "student", "child");

        initializeValidations();

        logger.info("CreateAccountController initialized");
    }

    private void initializeServices() {
        userRepository = new UserRepository();

        userService = new UserService(userRepository);
    }

    private void initializeValidations() {
        validationCreateAccount = new ValidationSupport();
        validationCreateAccount.registerValidator(usernameTextField, Validator.createEmptyValidator("The username must not be empty."));
        validationCreateAccount.registerValidator(passwordPasswordField, Validator.createEmptyValidator("The password must not be empty."));
        validationCreateAccount.registerValidator(firstNameTextField, Validator.createEmptyValidator("The first name must not be empty."));
        validationCreateAccount.registerValidator(lastNameTextField, Validator.createEmptyValidator("The last name must not be empty."));
        validationCreateAccount.registerValidator(phoneNumberTextField, Validator.createEmptyValidator("The phone number must not be empty."));
        validationCreateAccount.registerValidator(roleChoiceBox, Validator.createEmptyValidator("The role must not be empty."));
        dateOfBirthDatePicker.getEditor().setDisable(true);
        createButton.disableProperty().bind(validationCreateAccount.invalidProperty());
    }

    @FXML
    public void createActionHandler(ActionEvent event) {
        handleCreate();
    }

    private void handleCreate() {
        UserCreateView userCreateView = new UserCreateView();
        userCreateView.setUsername(usernameTextField.getText());
        userCreateView.setPassword(passwordPasswordField.getText().toCharArray());
        userCreateView.setFistName(firstNameTextField.getText());
        userCreateView.setLastName(lastNameTextField.getText());
        userCreateView.setRole(roleChoiceBox.getValue());
        userCreateView.setPhoneNumber(phoneNumberTextField.getText());
        userCreateView.setEmail(emailTextField.getText());
        userCreateView.setDateOfBirth(dateOfBirthDatePicker.getValue());

        try {
            userService.createUser(userCreateView);
            Stage stage = (Stage) createButton.getScene().getWindow();
            stage.close();
        }
        catch (StructureViolationException e) {
            Alert alertDialog = new Alert(Alert.AlertType.ERROR);
            alertDialog.setTitle("User creation error");
            alertDialog.setHeaderText(e.getMessage());
            alertDialog.setContentText("Click OK to continue");
            alertDialog.showAndWait();
        }
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

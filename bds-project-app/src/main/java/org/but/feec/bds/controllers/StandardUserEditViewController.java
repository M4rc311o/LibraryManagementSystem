package org.but.feec.bds.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.but.feec.bds.api.UserDeatiledView;
import org.but.feec.bds.api.StandardUserEditView;
import org.but.feec.bds.data.UserRepository;
import org.but.feec.bds.services.UserService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandardUserEditViewController {
    private static final Logger logger = LoggerFactory.getLogger(StandardUserDetailedViewController.class);

    @FXML
    private Label titleLabel;
    @FXML
    private Label idValueLabel;
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
    private DatePicker dateOfBirthDatePicker;
    @FXML
    private ChoiceBox<String> roleChoiceBox;
    @FXML
    private Button cancelButton;
    @FXML
    private Button applyButton;

    private UserRepository userRepository;
    private UserService userService;

    private ValidationSupport validationUserEdit;

    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    private Runnable refreshCallback;

    public void setRefreshCallback(Runnable refreshCallback) {
        this.refreshCallback = refreshCallback;
    }

    @FXML
    private void initialize() {
        initializeServices();

        roleChoiceBox.getItems().addAll("basic", "student", "child");

        initializeValidations();
        loadStandardUserData();

        logger.info("StandardUserEditViewController initialized");
    }

    private void initializeServices() {
        userRepository = new UserRepository();

        userService = new UserService(userRepository);
    }

    private void initializeValidations() {
        validationUserEdit = new ValidationSupport();
        validationUserEdit.registerValidator(firstNameTextField, Validator.createEmptyValidator("The first name must not be empty."));
        validationUserEdit.registerValidator(lastNameTextField, Validator.createEmptyValidator("The last name must not be empty."));
        validationUserEdit.registerValidator(phoneNumberTextField, Validator.createEmptyValidator("The phone number must not be empty."));
        //validationUserEdit.registerValidator(emailTextField, Validator.createRegexValidator("Invalid email format", "^$|^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", Severity.ERROR));
        applyButton.disableProperty().bind(validationUserEdit.invalidProperty());
    }

    private void loadStandardUserData() {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof UserDeatiledView standardUserDeatiledView) {
            idValueLabel.setText(String.valueOf(standardUserDeatiledView.getId()));
            usernameValueLabel.setText(standardUserDeatiledView.getUsername());
            firstNameTextField.setText(standardUserDeatiledView.getFirstName());
            lastNameTextField.setText(standardUserDeatiledView.getLastName());
            roleChoiceBox.setValue(standardUserDeatiledView.getRole());
            phoneNumberTextField.setText(standardUserDeatiledView.getPhoneNumber());
            emailTextField.setText(standardUserDeatiledView.getEmail());
            dateOfBirthDatePicker.setValue(standardUserDeatiledView.getDateOfBirth().toLocalDate());
        }
    }

    @FXML
    public void applyActionHandler(ActionEvent event) {
        handleApply();
    }

    private void handleApply() {
        StandardUserEditView standardUserEditView = new StandardUserEditView();
        standardUserEditView.setId(Long.valueOf(idValueLabel.getText()));
        standardUserEditView.setFirstName(firstNameTextField.getText());
        standardUserEditView.setLastName(lastNameTextField.getText());
        standardUserEditView.setRole(roleChoiceBox.getValue());
        standardUserEditView.setPhoneNumber(phoneNumberTextField.getText());
        standardUserEditView.setEmail(emailTextField.getText());
        standardUserEditView.setDateOfBirth(dateOfBirthDatePicker.getValue());

        userService.editStandardUser(standardUserEditView);
        refreshCallback.run();

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

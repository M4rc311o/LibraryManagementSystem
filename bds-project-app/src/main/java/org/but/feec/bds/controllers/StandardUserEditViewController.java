package org.but.feec.bds.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.but.feec.bds.api.StandardUserDetailedView;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;

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

    private ValidationSupport validation;

    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        ObservableList<String> roleChoiceBoxOptions =  FXCollections.observableArrayList("basic", "student", "child");
        roleChoiceBox.setItems(roleChoiceBoxOptions);

        initializeValidations();
        loadStandardUserData();

        logger.info("StandardUserEditViewController initialized");
    }

    private void initializeValidations() {
        validation = new ValidationSupport();
        validation.registerValidator(firstNameTextField, Validator.createEmptyValidator("The first name must not be empty."));
        validation.registerValidator(lastNameTextField, Validator.createEmptyValidator("The last name must not be empty."));
        validation.registerValidator(phoneNumberTextField, Validator.createEmptyValidator("The phone number must not be empty."));
        validation.registerValidator(emailTextField, false, Validator.createRegexValidator("Invalid email format", "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", Severity.ERROR));
    }

    private void loadStandardUserData() {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof StandardUserDetailedView standardUserDetailedView) {
            idValueLabel.setText(String.valueOf(standardUserDetailedView.getId()));
            usernameValueLabel.setText(standardUserDetailedView.getUsername());
            firstNameTextField.setText(standardUserDetailedView.getFirstName());
            lastNameTextField.setText(standardUserDetailedView.getLastName());
            roleChoiceBox.setValue(standardUserDetailedView.getRole());
            phoneNumberTextField.setText(standardUserDetailedView.getPhoneNumber());
            emailTextField.setText(standardUserDetailedView.getEmail());
            dateOfBirthDatePicker.setValue(standardUserDetailedView.getDateOfBirth().toLocalDate());
        }
    }

    public void applyActionHandler(ActionEvent event) {
        handleApply();
    }

    private void handleApply() {
        //TODO: handle apply changes
    }

    public void cancelActionHandler(ActionEvent event) {
        handleCancel();
    }

    private void handleCancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}

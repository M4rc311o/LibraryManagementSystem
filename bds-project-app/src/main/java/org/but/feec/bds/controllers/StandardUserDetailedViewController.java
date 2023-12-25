package org.but.feec.bds.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.but.feec.bds.api.StandardUserDetailedView;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class StandardUserDetailedViewController {
    private static final Logger logger = LoggerFactory.getLogger(StandardUserDetailedViewController.class);

    @FXML
    private Label titleLabel;
    @FXML
    private Label idValueLabel;
    @FXML
    private Label usernameValueLabel;
    @FXML
    private Label firstNameValueLabel;
    @FXML
    private Label lastNameValueLabel;
    @FXML
    private Label roleValueLabel;
    @FXML
    private Label phoneNumberValueLabel;
    @FXML
    private Label emailValueLabel;
    @FXML
    private Label dateOfBirthValueLabel;

    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        loadStandardUserData();

        logger.info("StandardUserDetailedViewController initialized");
    }

    private void loadStandardUserData() {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof StandardUserDetailedView standardUserDetailedView) {
            idValueLabel.setText(String.valueOf(standardUserDetailedView.getId()));
            usernameValueLabel.setText(standardUserDetailedView.getUsername());
            firstNameValueLabel.setText(standardUserDetailedView.getFirstName());
            lastNameValueLabel.setText(standardUserDetailedView.getLastName());
            roleValueLabel.setText(standardUserDetailedView.getRole());
            phoneNumberValueLabel.setText(standardUserDetailedView.getPhoneNumber());
            emailValueLabel.setText(standardUserDetailedView.getEmail());
            dateOfBirthValueLabel.setText(standardUserDetailedView.getDateOfBirth().toString());
        }
    }
}

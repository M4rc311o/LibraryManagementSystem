package org.but.feec.bds.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.but.feec.bds.api.UserDeatiledView;
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
        if (stage.getUserData() instanceof UserDeatiledView standardUserDeatiledView) {
            idValueLabel.setText(String.valueOf(standardUserDeatiledView.getId()));
            usernameValueLabel.setText(standardUserDeatiledView.getUsername());
            firstNameValueLabel.setText(standardUserDeatiledView.getFirstName());
            lastNameValueLabel.setText(standardUserDeatiledView.getLastName());
            roleValueLabel.setText(standardUserDeatiledView.getRole());
            phoneNumberValueLabel.setText(standardUserDeatiledView.getPhoneNumber());
            emailValueLabel.setText(standardUserDeatiledView.getEmail());
            dateOfBirthValueLabel.setText(standardUserDeatiledView.getDateOfBirth().toString());
        }
    }
}

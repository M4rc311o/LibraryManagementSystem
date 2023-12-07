package org.but.feec.bds.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import org.but.feec.bds.data.UserRepository;
import org.but.feec.bds.exceptions.DataAccessException;
import org.but.feec.bds.exceptions.ResourceNotFoundException;
import org.but.feec.bds.services.AuthService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private AuthService authService;

    @FXML
    public Label usernameLabel;
    @FXML
    public Label passwordLabel;
    @FXML
    public ImageView lmsLogoImageView;
    @FXML
    private Button signInButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private void initialize() {
        usernameTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleSignIn();
            }
        });
        passwordPasswordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleSignIn();
            }
        });

        initializeServices();
        initializeValidations();

        logger.info("LoginController initialized");
    }

    private void initializeServices() {
        UserRepository userRepository = new UserRepository();
        authService = new AuthService(userRepository);
    }

    private void initializeValidations() {
        ValidationSupport validation = new ValidationSupport();
        validation.registerValidator(usernameTextField, Validator.createEmptyValidator("The username must not be empty."));
        validation.registerValidator(passwordPasswordField, Validator.createEmptyValidator("The password must not be empty."));
        signInButton.disableProperty().bind(validation.invalidProperty());
    }

    public void signInActionHandler(ActionEvent event) {
        handleSignIn();
    }

    private void handleSignIn() {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();

        try {
            boolean authenticated = authService.authenticate(username, password);
            if (authenticated) {
                //TODO: OPEN USER STAGE; PROBABLY SWITCH BASED ON ROLE
                showValidCredentialsDialog();
            }
            else {
                showInvalidCredentialsDialog();
            }
        }
        catch (ResourceNotFoundException | DataAccessException e) {
            showInvalidCredentialsDialog();
        }
    }

    private void showInvalidCredentialsDialog() {
        Alert alertDialog = new Alert(Alert.AlertType.ERROR);
        alertDialog.setTitle("Unauthenticated");
        alertDialog.setHeaderText("The user is not authenticated");
        alertDialog.setContentText("Please provide a valid username and password");
        alertDialog.showAndWait();
    }

    private void showValidCredentialsDialog()  {
        Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION);
        alertDialog.setTitle("Authenticated");
        alertDialog.setHeaderText("You were successfully logged in");
        alertDialog.setContentText("Click OK and continue");

        Optional<ButtonType> result = alertDialog.showAndWait();

        if (result.get() == ButtonType.OK) {
            System.out.println("ok clicked");
        }
        else {
            System.out.println("cancel clicked");
        }
    }
}

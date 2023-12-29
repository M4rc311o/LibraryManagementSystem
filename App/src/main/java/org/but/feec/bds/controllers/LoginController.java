package org.but.feec.bds.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.but.feec.bds.App;
import org.but.feec.bds.data.UserRepository;
import org.but.feec.bds.data.UserSession;
import org.but.feec.bds.exceptions.DataAccessException;
import org.but.feec.bds.exceptions.ExceptionHandler;
import org.but.feec.bds.exceptions.ResourceNotFoundException;
import org.but.feec.bds.services.AuthService;
import org.but.feec.bds.services.SessionService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

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
    private Button newAccountButton;

    private AuthService authService;
    private UserRepository userRepository;
    private ValidationSupport validation;
    private SessionService sessionService;

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
        userRepository = new UserRepository();

        authService = new AuthService(userRepository);
        sessionService = new SessionService(userRepository, UserSession.getSession());
    }

    private void initializeValidations() {
        validation = new ValidationSupport();
        validation.registerValidator(usernameTextField, Validator.createEmptyValidator("The username must not be empty."));
        validation.registerValidator(passwordPasswordField, Validator.createEmptyValidator("The password must not be empty."));
        signInButton.disableProperty().bind(validation.invalidProperty());
    }

   @FXML
    public void signInActionHandler(ActionEvent event) {
        handleSignIn();
    }

    private void handleSignIn() {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();

        try {
            boolean authenticated = authService.authenticate(username, password);
            if (authenticated) {
                showValidCredentialsDialog();
                switch (sessionService.getCurrentRole()){
                    case "librarian", "administrator":
                        showLibrarianView();
                        break;
                    case "basic", "student", "child":
                        showStandardUserView();
                        break;
                }

            }
            else {
                showInvalidCredentialsDialog();
            }
        }
        catch (ResourceNotFoundException | DataAccessException e) {
            showInvalidCredentialsDialog();
        }
    }

    @FXML
    public void newAccountActionHandler(ActionEvent event) {
        handleNewAccount();
    }

    private void handleNewAccount() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/CreateAccount.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 407, 420);
            Stage stage = new Stage();
            stage.setTitle("LMS Create account");
            stage.setScene(scene);

            stage.getIcons().add(new Image(App.class.getResourceAsStream("images/lms_logo.png")));
            stage.show();
        }
        catch (IOException e) {
            ExceptionHandler.handleException(e);
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
        alertDialog.setContentText("Click OK to continue");

        alertDialog.showAndWait();
    }

    private void showStandardUserView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/StandardUser.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 930, 610);
            Stage stage = new Stage();
            stage.setTitle("Library management system");
            stage.setScene(scene);

            Stage stageOld = (Stage) signInButton.getScene().getWindow();
            stageOld.close();

            stage.getIcons().add(new Image(App.class.getResourceAsStream("images/lms_logo.png")));
            stage.show();
        }
        catch (IOException e) {
            ExceptionHandler.handleException(e);
        }
    }

    private void showLibrarianView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/Librarian.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 930, 610);
            Stage stage = new Stage();
            stage.setTitle("Library management system");
            stage.setScene(scene);

            Stage stageOld = (Stage) signInButton.getScene().getWindow();
            stageOld.close();

            stage.getIcons().add(new Image(App.class.getResourceAsStream("images/lms_logo.png")));
            stage.show();
        }
        catch (IOException e) {
            ExceptionHandler.handleException(e);
        }
    }
}

package org.but.feec.bds.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.but.feec.bds.api.BookRequestEditView;
import org.but.feec.bds.api.BookRequestSimpleView;
import org.but.feec.bds.data.BookRequestRepository;
import org.but.feec.bds.services.BookRequestService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookRequestEditViewController {
    private static final Logger logger = LoggerFactory.getLogger(BookRequestEditViewController.class);

    @FXML
    private Label titleLabel;
    @FXML
    private Label idValueLabel;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField isbnTextField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button applyButton;

    private BookRequestRepository bookRequestRepository;
    private BookRequestService bookRequestService;

    private ValidationSupport validationBookRequestEdit;

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
        initializeValidations();
        loadBookRequestData();
    }

    private void initializeServices() {
        bookRequestRepository = new BookRequestRepository();

        bookRequestService = new BookRequestService(bookRequestRepository);
    }

    private void initializeValidations() {
        validationBookRequestEdit = new ValidationSupport();
        validationBookRequestEdit.registerValidator(titleTextField, Validator.createEmptyValidator("The title must not be empty."));
        validationBookRequestEdit.registerValidator(isbnTextField, Validator.createEmptyValidator("The isbn must not be empty."));
        applyButton.disableProperty().bind(validationBookRequestEdit.invalidProperty());
    }

    private void loadBookRequestData() {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof BookRequestSimpleView bookRequestSimpleView) {
            idValueLabel.setText(String.valueOf(bookRequestSimpleView.getId()));
            titleTextField.setText(bookRequestSimpleView.getTitle());
            isbnTextField.setText(bookRequestSimpleView.getIsbn());
        }
    }

    @FXML
    public void applyActionHandler(ActionEvent event) {
        handleApply();
    }

    private void handleApply() {
        BookRequestEditView bookRequestEditView = new BookRequestEditView();
        bookRequestEditView.setId(Long.valueOf(idValueLabel.getText()));
        bookRequestEditView.setTitle(titleTextField.getText());
        bookRequestEditView.setIsbn(isbnTextField.getText());

        bookRequestService.editBookRequest(bookRequestEditView);
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

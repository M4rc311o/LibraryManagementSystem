package org.but.feec.bds.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.but.feec.bds.api.LoanBookCreateView;
import org.but.feec.bds.data.LoanRepository;
import org.but.feec.bds.data.PhysicalBookRepository;
import org.but.feec.bds.exceptions.StructureViolationException;
import org.but.feec.bds.services.LoanService;
import org.but.feec.bds.services.PhysicalBookService;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class LoanBookController {
    private static final Logger logger = LoggerFactory.getLogger(LoanBookController.class);

    @FXML
    private Label titleLabel;
    @FXML
    private TextField userIdTextField;
    @FXML
    private TextField physicalBookIdTextField;
    @FXML
    private DatePicker issueDateDatePicker;
    @FXML
    private DatePicker dueDateDatePicker;
    @FXML
    private Button cancelButton;
    @FXML
    private Button applyButton;

    private LoanRepository loanRepository;
    private LoanService loanService;
    private PhysicalBookRepository physicalBookRepository;
    private PhysicalBookService physicalBookService;
    private ValidationSupport validationLoanBook;

    private Runnable refreshCallback;

    public void setRefreshCallback(Runnable refreshCallback) {
        this.refreshCallback = refreshCallback;
    }

    @FXML
    private void initialize() {
        initializeServices();
        initializeValidations();

        issueDateDatePicker.setValue(LocalDate.now());
        dueDateDatePicker.setValue(LocalDate.now().plusMonths(1));;

        logger.info("LoanBookController initialized");
    }

    private void initializeServices() {
        loanRepository = new LoanRepository();
        physicalBookRepository = new PhysicalBookRepository();

        loanService = new LoanService(loanRepository);
        physicalBookService = new PhysicalBookService(physicalBookRepository);
    }

    private void initializeValidations() {
        validationLoanBook = new ValidationSupport();
        validationLoanBook.registerValidator(userIdTextField, Validator.createRegexValidator("Invalid user id format", "^[1-9]\\d*$", Severity.ERROR));
        validationLoanBook.registerValidator(physicalBookIdTextField, Validator.createRegexValidator("Invalid physical book id format", "^[1-9]\\d*$", Severity.ERROR));
        validationLoanBook.registerValidator(issueDateDatePicker, Validator.createEmptyValidator("The issue date must not be empty"));
        validationLoanBook.registerValidator(dueDateDatePicker, Validator.createEmptyValidator("The due date must not be empty"));
        issueDateDatePicker.getEditor().setDisable(true);
        dueDateDatePicker.getEditor().setDisable(true);
        applyButton.disableProperty().bind(validationLoanBook.invalidProperty());
    }

    @FXML
    public void applyActionHandler(ActionEvent event) {
        handleApply();
    }

    private void handleApply() {
        LoanBookCreateView loanBookCreateView = new LoanBookCreateView();
        loanBookCreateView.setUserId(Long.valueOf(userIdTextField.getText()));
        loanBookCreateView.setPhysicalBookId(Long.valueOf(physicalBookIdTextField.getText()));
        loanBookCreateView.setIssueDate(issueDateDatePicker.getValue());
        loanBookCreateView.setDueDate(dueDateDatePicker.getValue());
        loanBookCreateView.setLibraryId(physicalBookService.getPhysicalBookLibraryId(Long.valueOf(physicalBookIdTextField.getText())));

        try {
            loanService.createLoan(loanBookCreateView);
        }
        catch (StructureViolationException e) {
            Alert alertDialog = new Alert(Alert.AlertType.ERROR);
            alertDialog.setTitle("Loan creation error");
            alertDialog.setHeaderText(e.getMessage());
            alertDialog.setContentText("Click OK to continue");
            alertDialog.showAndWait();
        }
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

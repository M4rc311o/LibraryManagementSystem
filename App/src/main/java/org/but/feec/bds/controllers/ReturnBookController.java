package org.but.feec.bds.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.but.feec.bds.api.LibraryNameWithIdIdentifier;
import org.but.feec.bds.api.ReturnBookView;
import org.but.feec.bds.data.LibraryRepository;
import org.but.feec.bds.data.LoanRepository;
import org.but.feec.bds.data.PhysicalBookRepository;
import org.but.feec.bds.exceptions.StructureViolationException;
import org.but.feec.bds.services.LibraryService;
import org.but.feec.bds.services.LoanService;
import org.but.feec.bds.services.PhysicalBookService;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;


public class ReturnBookController {
    private static final Logger logger = LoggerFactory.getLogger(ReturnBookController.class);

    @FXML
    private Label titleLabel;
    @FXML
    private TextField physicalBookIdTextField;
    @FXML
    private TextField feeTextField;
    @FXML
    private TextArea conditionTextArea;
    @FXML
    private ChoiceBox<LibraryNameWithIdIdentifier> libraryChoiceBox;
    @FXML
    private Button cancelButton;
    @FXML
    private Button applyButton;


    private LibraryRepository libraryRepository;
    private LibraryService libraryService;
    private PhysicalBookRepository physicalBookRepository;
    private PhysicalBookService physicalBookService;
    private LoanService loanService;
    private LoanRepository loanRepository;
    private ValidationSupport validationReturnBook;
    private Validator<String> physicalBookIdTextFieldValidator;

    private Runnable refreshCallback;

    public void setRefreshCallback(Runnable refreshCallback) {
        this.refreshCallback = refreshCallback;
    }

    @FXML
    private void initialize() {
        initializeServices();
        initializeValidations();

        libraryChoiceBox.getItems().addAll(libraryService.getLibrariesNamesWithIdIdentifier());
        feeTextField.setText("0.00");
        physicalBookIdTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            ValidationResult validationResult = physicalBookIdTextFieldValidator.apply(physicalBookIdTextField, physicalBookIdTextField.getText());
            if (!newValue && validationResult.getErrors().isEmpty()) {
                conditionTextArea.setText(physicalBookService.getPhysicalBookDetailedViewById(Long.valueOf(physicalBookIdTextField.getText())).getCondition());
            }
        });

        logger.info("ReturnBookController initialized");
    }

    private void initializeServices() {
        libraryRepository = new LibraryRepository();
        physicalBookRepository = new PhysicalBookRepository();
        loanRepository = new LoanRepository();

        libraryService = new LibraryService(libraryRepository);
        physicalBookService = new PhysicalBookService(physicalBookRepository);
        loanService = new LoanService(loanRepository);
    }

    private void initializeValidations() {
        validationReturnBook = new ValidationSupport();
        physicalBookIdTextFieldValidator = Validator.createRegexValidator("Invalid physical book id format", "^[1-9]\\d*$", Severity.ERROR);
        validationReturnBook.registerValidator(physicalBookIdTextField, physicalBookIdTextFieldValidator);
        validationReturnBook.registerValidator(feeTextField, Validator.createRegexValidator("Invalid fee format", "^([1-9]+\\d*|0+)(\\.\\d{1,2})?$", Severity.ERROR));
        validationReturnBook.registerValidator(libraryChoiceBox, Validator.createEmptyValidator("The library must not be empty"));
        applyButton.disableProperty().bind(validationReturnBook.invalidProperty());
    }

    @FXML
    public void applyActionHandler(ActionEvent event) {
        handleApply();
    }

    private void handleApply() {
        ReturnBookView returnBookView = new ReturnBookView();
        returnBookView.setId(Long.valueOf(physicalBookIdTextField.getText()));
        returnBookView.setFee(new BigDecimal(feeTextField.getText()));
        returnBookView.setCondition(conditionTextArea.getText());
        returnBookView.setLibraryId(libraryChoiceBox.getValue().getId());

        try {
            loanService.returnBook(returnBookView);
        }
        catch (StructureViolationException e) {
            Alert alertDialog = new Alert(Alert.AlertType.ERROR);
            alertDialog.setTitle("Book return error");
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

package org.but.feec.bds.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.but.feec.bds.api.BookCreateView;
import org.but.feec.bds.api.BookDetailedView;
import org.but.feec.bds.api.LibraryNameWithIdIdentifier;
import org.but.feec.bds.api.PhysicalBookCreateView;
import org.but.feec.bds.data.BookRepository;
import org.but.feec.bds.data.LibraryRepository;
import org.but.feec.bds.data.PhysicalBookRepository;
import org.but.feec.bds.services.BookService;
import org.but.feec.bds.services.LibraryService;
import org.but.feec.bds.services.PhysicalBookService;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class AddBookController {
    private static final Logger logger = LoggerFactory.getLogger(AddBookController.class);

    @FXML
    private Label titleLabel;
    @FXML
    private TextField isbnTextField;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField yearTextField;
    @FXML
    private TextField evaluationTextField;
    @FXML
    private ChoiceBox<String> genreChoiceBox;
    @FXML
    private ChoiceBox<String> languageChoiceBox;
    @FXML
    private ChoiceBox<String> literaryPeriodChoiceBox;
    @FXML
    private ChoiceBox<String> bindingChoiceBox;
    @FXML
    private ChoiceBox<LibraryNameWithIdIdentifier> libraryChoiceBox;
    @FXML
    private TextArea conditionTextArea;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;

    private LibraryRepository libraryRepository;
    private LibraryService libraryService;
    private BookRepository bookRepository;
    private BookService bookService;
    private PhysicalBookRepository physicalBookRepository;
    private PhysicalBookService physicalBookService;

    private ValidationSupport validationAddBook;
    private Validator<String> bookIsbnTextFieldValidator;

    private Runnable refreshCallback;

    public void setRefreshCallback(Runnable refreshCallback) {
        this.refreshCallback = refreshCallback;
    }

    @FXML
    private void initialize() {
        initializeService();
        initializeValidations();

        genreChoiceBox.getItems().addAll(bookService.getBookGenres());
        languageChoiceBox.getItems().addAll(bookService.getBookLanguages());
        bindingChoiceBox.getItems().addAll(bookService.getBookBindings());
        literaryPeriodChoiceBox.getItems().addAll(bookService.getBookLiteraryPeriods());
        libraryChoiceBox.getItems().addAll(libraryService.getLibrariesNamesWithIdIdentifier());

        isbnTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            ValidationResult validationResult = bookIsbnTextFieldValidator.apply(isbnTextField, isbnTextField.getText());
            if (!newValue && validationResult.getErrors().isEmpty()) {
                BookDetailedView bookDetailedView = bookService.getBookDetailedViewByIsbn(isbnTextField.getText());
                titleTextField.setText(bookDetailedView.getTitle());
                titleTextField.setEditable(false);
                yearTextField.setText(String.valueOf(bookDetailedView.getYear()));
                yearTextField.setEditable(false);
                evaluationTextField.setText(String.valueOf(bookDetailedView.getEvaluation()));
                evaluationTextField.setEditable(false);
                genreChoiceBox.setValue(bookDetailedView.getGenre());
                genreChoiceBox.setDisable(true);
                languageChoiceBox.setValue(bookDetailedView.getLanguage());
                languageChoiceBox.setDisable(true);
                bindingChoiceBox.setValue(bookDetailedView.getBinding());
                bindingChoiceBox.setDisable(true);
                literaryPeriodChoiceBox.setValue(bookDetailedView.getLiteraryPeriod());
                literaryPeriodChoiceBox.setDisable(true);
            }
        });

        logger.info("AddBookController initialized");
    }

    private void initializeService() {
        libraryRepository = new LibraryRepository();
        bookRepository = new BookRepository();
        physicalBookRepository = new PhysicalBookRepository();

        libraryService = new LibraryService(libraryRepository);
        bookService = new BookService(bookRepository);
        physicalBookService = new PhysicalBookService(physicalBookRepository);
    }

    private void initializeValidations() {
        validationAddBook = new ValidationSupport();
        bookIsbnTextFieldValidator = Validator.createEmptyValidator("The ISBN must not be empty");
        validationAddBook.registerValidator(isbnTextField, bookIsbnTextFieldValidator);
        validationAddBook.registerValidator(titleTextField, Validator.createEmptyValidator("The title must not be empty"));
        validationAddBook.registerValidator(yearTextField, Validator.createRegexValidator("Invalid year format", "^\\d+$", Severity.ERROR));
        validationAddBook.registerValidator(evaluationTextField, Validator.createRegexValidator("Invalid evaluation format", "^([1-9]+\\d*|0+)(\\.\\d{1,2})?$", Severity.ERROR));
        addButton.disableProperty().bind(validationAddBook.invalidProperty());
    }

    @FXML
    public void addActionHandler(ActionEvent event) {
        handleAdd();
    }

    private void handleAdd() {
        Long bookId = null;
        if (!bookService.bookExists(isbnTextField.getText())){
            BookCreateView bookCreateView = new BookCreateView();
            bookCreateView.setIsbn(isbnTextField.getText());
            bookCreateView.setBinding(bindingChoiceBox.getValue());
            bookCreateView.setEvaluation(new BigDecimal(evaluationTextField.getText()));
            bookCreateView.setTitle(titleTextField.getText());
            bookCreateView.setGenre(genreChoiceBox.getValue());
            bookCreateView.setYear(Integer.parseInt(yearTextField.getText()));
            bookCreateView.setLiteraryPeriod(literaryPeriodChoiceBox.getValue());
            bookCreateView.setLanguage(languageChoiceBox.getValue());
            bookId = bookService.createBook(bookCreateView);
        }
        PhysicalBookCreateView physicalBookCreateView = new PhysicalBookCreateView();
        physicalBookCreateView.setBookId(bookId == null ? bookService.getBookDetailedViewByIsbn(isbnTextField.getText()).getId() : bookId);
        physicalBookCreateView.setCondition(conditionTextArea.getText());
        physicalBookCreateView.setLibraryId(libraryChoiceBox.getValue().getId());
        physicalBookService.createPhysicalBook(physicalBookCreateView);

        refreshCallback.run();

        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancelActionHandler(javafx.event.ActionEvent event) {
        handleCancel();
    }

    private void handleCancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}

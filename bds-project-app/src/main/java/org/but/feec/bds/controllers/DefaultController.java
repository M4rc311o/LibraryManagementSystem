package org.but.feec.bds.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.but.feec.bds.api.BookRequestCreateView;
import org.but.feec.bds.api.BookSimpleView;
import org.but.feec.bds.data.BookRepository;
import org.but.feec.bds.data.BookRequestRepository;
import org.but.feec.bds.data.UserRepository;
import org.but.feec.bds.data.UserSession;
import org.but.feec.bds.services.BookRequestService;
import org.but.feec.bds.services.BookService;
import org.but.feec.bds.services.SessionService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class DefaultController {
    private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @FXML
    public Label usernameLabel;
    @FXML
    private Button signOutButton;

    @FXML
    public Tab booksTab;
    @FXML
    private TableColumn<BookSimpleView, String> bookTitleColumn;
    @FXML
    private TableColumn<BookSimpleView, String > bookIsbnColumn;
    @FXML
    private TableColumn<BookSimpleView, Integer > bookYearColumn;
    @FXML
    private TableColumn<BookSimpleView, Float > bookEvaluationColumn;
    @FXML
    private TableColumn<BookSimpleView, String > bookLanguageColumn;
    @FXML
    private TableView<BookSimpleView> booksTableView;

    @FXML
    public Tab bookRequestTab;
    @FXML
    public Label headingBookRequestLabel;
    @FXML
    public Label titleBookRequestLabel;
    @FXML
    public Label isbnBookRequestLabel;
    @FXML
    private TextField titleBookRequestTextField;
    @FXML
    private TextField isbnBookRequestTextField;
    @FXML
    private Button submitButton;

    private BookService bookService;
    private BookRepository bookRepository;
    private ValidationSupport validationBookRequest;
    private BookRequestService bookRequestService;
    private BookRequestRepository bookRequestRepository;
    private SessionService sessionService;
    private UserRepository userRepository;

    @FXML
    private void initialize() {
        initializeServices();

        usernameLabel.setText(sessionService.getCurrentUsername());

        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<BookSimpleView, String>("title"));
        bookIsbnColumn.setCellValueFactory(new PropertyValueFactory<BookSimpleView, String>("isbn"));
        bookYearColumn.setCellValueFactory(new PropertyValueFactory<BookSimpleView, Integer>("year"));
        bookEvaluationColumn.setCellValueFactory(new PropertyValueFactory<BookSimpleView, Float>("evaluation"));
        bookLanguageColumn.setCellValueFactory(new PropertyValueFactory<BookSimpleView, String>("language"));
        ObservableList<BookSimpleView> observableBookSimpleViews = initializeBooksData();
        booksTableView.setItems(observableBookSimpleViews);
        booksTableView.getSortOrder().add(bookTitleColumn);

        initializeValidations();

        logger.info("DefaultController initialized");
    }

    private void initializeServices() {
        bookRepository = new BookRepository();
        bookRequestRepository = new BookRequestRepository();
        userRepository = new UserRepository();

        bookService = new BookService(bookRepository);
        bookRequestService = new BookRequestService(bookRequestRepository);
        sessionService = new SessionService(userRepository, UserSession.getSession());
    }

    private ObservableList<BookSimpleView> initializeBooksData() {
        List<BookSimpleView> bookSimpleViews = bookService.getBooksSimpleView();
        return FXCollections.observableArrayList(bookSimpleViews);
    }

    private void initializeValidations() {
        validationBookRequest = new ValidationSupport();
        validationBookRequest.registerValidator(isbnBookRequestTextField, Validator.createEmptyValidator("ISBN must not be empty"));
        validationBookRequest.registerValidator(titleBookRequestTextField, Validator.createEmptyValidator("Title must not be empty"));
        submitButton.disableProperty().bind(validationBookRequest.invalidProperty());
    }

    public void submitBookRequestActionHandler(ActionEvent event) {
        handleSubmitBookRequest();
    }

    public void handleSubmitBookRequest() {
        String title = titleBookRequestTextField.getText();
        String isbn = isbnBookRequestTextField.getText();
        long userId = sessionService.getCurrentUserId();

        BookRequestCreateView bookRequestCreateView = new BookRequestCreateView();
        bookRequestCreateView.setTitle(title);
        bookRequestCreateView.setIsbn(isbn);
        bookRequestCreateView.setUserId(userId);

        bookRequestService.createBookRequest(bookRequestCreateView);

        bookRequestCreatedConfirmationDialog();

        titleBookRequestTextField.clear();
        isbnBookRequestTextField.clear();
    }

    private void bookRequestCreatedConfirmationDialog() {
        Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION);
        alertDialog.setTitle("Book request created");
        alertDialog.setHeaderText("Your book request was successfully created.");
        alertDialog.setContentText("Click OK to continue");

        Optional<ButtonType> result = alertDialog.showAndWait();

        if (result.get() == ButtonType.OK) {
            System.out.println("ok clicked");
        }
        else {
            System.out.println("cancel clicked");
        }

    }
}

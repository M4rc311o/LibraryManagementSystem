package org.but.feec.bds.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.but.feec.bds.App;
import org.but.feec.bds.api.BookRequestCreateView;
import org.but.feec.bds.api.BookSimpleView;
import org.but.feec.bds.api.LibrarySimpleView;
import org.but.feec.bds.api.LoanSimpleView;
import org.but.feec.bds.data.*;
import org.but.feec.bds.exceptions.ExceptionHandler;
import org.but.feec.bds.services.*;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class StandardUserController {
    private static final Logger logger = LoggerFactory.getLogger(StandardUserController.class);

    @FXML
    public Label usernameLabel;
    @FXML
    private Button signOutButton;

    @FXML
    public Tab booksTab;
    @FXML
    private TableColumn<BookSimpleView, String> bookTitleColumn;
    @FXML
    private TableColumn<BookSimpleView, String> bookIsbnColumn;
    @FXML
    private TableColumn<BookSimpleView, Integer> bookYearColumn;
    @FXML
    private TableColumn<BookSimpleView, Float> bookEvaluationColumn;
    @FXML
    private TableColumn<BookSimpleView, String> bookLanguageColumn;
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

    @FXML
    private Tab librariesTab;
    @FXML
    private TableColumn<LibrarySimpleView, Long> libraryIdColumn;
    @FXML
    private TableColumn<LibrarySimpleView, String> libraryNameColumn;
    @FXML
    private TableColumn<LibrarySimpleView, String> libraryCountryColumn;
    @FXML
    private TableColumn<LibrarySimpleView, String> libraryCityColumn;
    @FXML
    private TableColumn<LibrarySimpleView, String> libraryStreetColumn;
    @FXML
    private TableColumn<LibrarySimpleView, Integer> libraryHouseNumberColumn;
    @FXML
    private TableView<LibrarySimpleView> librariesTableView;

    @FXML
    private Tab loansTab;
    @FXML
    private TableColumn<LoanSimpleView, Long> loanIdColumn;
    @FXML
    private TableColumn<LoanSimpleView, String> loanBookNameColumn;
    @FXML
    private TableColumn<LoanSimpleView, Date> loanIssueDateColumn;
    @FXML
    private TableColumn<LoanSimpleView, Date> loanDueDateColumn;
    @FXML
    private TableColumn<LoanSimpleView, Boolean> loanReturnedColumn;
    @FXML
    private TableView<LoanSimpleView> loansTableView;

    private BookService bookService;
    private BookRepository bookRepository;
    private ValidationSupport validationBookRequest;
    private BookRequestService bookRequestService;
    private BookRequestRepository bookRequestRepository;
    private SessionService sessionService;
    private UserRepository userRepository;
    private LibraryRepository libraryRepository;
    private LibraryService libraryService;
    private LoanRepository loanRepository;
    private LoanService loanService;

    @FXML
    private void initialize() {
        initializeServices();

        usernameLabel.setText(sessionService.getCurrentUsername());

        loanIdColumn.setCellValueFactory(new PropertyValueFactory<LoanSimpleView, Long>("id"));
        loanBookNameColumn.setCellValueFactory(new PropertyValueFactory<LoanSimpleView, String>("bookName"));
        loanIssueDateColumn.setCellValueFactory(new PropertyValueFactory<LoanSimpleView, Date>("issueDate"));
        loanDueDateColumn.setCellValueFactory(new PropertyValueFactory<LoanSimpleView, Date>("dueDate"));
        loanReturnedColumn.setCellValueFactory(new PropertyValueFactory<LoanSimpleView, Boolean>("returned"));
        ObservableList<LoanSimpleView> observableLoanSimpleViews = initializeLoansData();
        loansTableView.setItems(observableLoanSimpleViews);
        loansTableView.getSortOrder().add(loanIdColumn);

        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<BookSimpleView, String>("title"));
        bookIsbnColumn.setCellValueFactory(new PropertyValueFactory<BookSimpleView, String>("isbn"));
        bookYearColumn.setCellValueFactory(new PropertyValueFactory<BookSimpleView, Integer>("year"));
        bookEvaluationColumn.setCellValueFactory(new PropertyValueFactory<BookSimpleView, Float>("evaluation"));
        bookLanguageColumn.setCellValueFactory(new PropertyValueFactory<BookSimpleView, String>("language"));
        ObservableList<BookSimpleView> observableBookSimpleViews = initializeBooksData();
        booksTableView.setItems(observableBookSimpleViews);
        booksTableView.getSortOrder().add(bookTitleColumn);

        libraryIdColumn.setCellValueFactory(new PropertyValueFactory<LibrarySimpleView, Long>("id"));
        libraryNameColumn.setCellValueFactory(new PropertyValueFactory<LibrarySimpleView, String>("name"));
        libraryCountryColumn.setCellValueFactory(new PropertyValueFactory<LibrarySimpleView, String>("country"));
        libraryCityColumn.setCellValueFactory(new PropertyValueFactory<LibrarySimpleView, String>("city"));
        libraryStreetColumn.setCellValueFactory(new PropertyValueFactory<LibrarySimpleView, String>("street"));
        libraryHouseNumberColumn.setCellValueFactory(new PropertyValueFactory<LibrarySimpleView, Integer>("houseNumber"));
        ObservableList<LibrarySimpleView> observableLibrariesViews = initializeLibrariesData();
        librariesTableView.setItems(observableLibrariesViews);
        librariesTableView.getSortOrder().add(libraryIdColumn);

        initializeValidations();

        logger.info("StandardUserController initialized");
    }

    private void initializeServices() {
        bookRepository = new BookRepository();
        bookRequestRepository = new BookRequestRepository();
        userRepository = new UserRepository();
        libraryRepository = new LibraryRepository();
        loanRepository = new LoanRepository();

        bookService = new BookService(bookRepository);
        bookRequestService = new BookRequestService(bookRequestRepository);
        sessionService = new SessionService(userRepository, UserSession.getSession());
        libraryService = new LibraryService(libraryRepository);
        loanService = new LoanService(loanRepository);
    }

    private ObservableList<LoanSimpleView> initializeLoansData() {
        List<LoanSimpleView> loanSimpleViews = loanService.getLoansSimpleViewForUser(sessionService.getCurrentUserId());
        return FXCollections.observableArrayList(loanSimpleViews);
    }

    private ObservableList<BookSimpleView> initializeBooksData() {
        List<BookSimpleView> bookSimpleViews = bookService.getBooksSimpleView();
        return FXCollections.observableArrayList(bookSimpleViews);
    }

    private ObservableList<LibrarySimpleView> initializeLibrariesData() {
        List<LibrarySimpleView> librarySimpleViews = libraryService.getLibrariesSimpleView();
        return FXCollections.observableArrayList(librarySimpleViews);
    }

    private void initializeValidations() {
        validationBookRequest = new ValidationSupport();
        validationBookRequest.registerValidator(isbnBookRequestTextField, Validator.createEmptyValidator("ISBN must not be empty"));
        validationBookRequest.registerValidator(titleBookRequestTextField, Validator.createEmptyValidator("Title must not be empty"));
        submitButton.disableProperty().bind(validationBookRequest.invalidProperty());
    }

    public void signOutActionHandler(ActionEvent event) {
        handleSignOut();
    }

    private void handleSignOut() {
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        stage.close();
    }

    public void submitBookRequestActionHandler(ActionEvent event) {
        handleSubmitBookRequest();
    }

    private void handleSubmitBookRequest() {
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

    @FXML
    public void manageAccountEventHandler(ActionEvent event) {
        handleManageAccount();
    }

    private void handleManageAccount() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/UserAccountEdit.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Manage account");
            stage.getIcons().add(new Image(App.class.getResourceAsStream("images/lms_logo.png")));
            Scene scene = new Scene(fxmlLoader.load(), 407, 440);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            ExceptionHandler.handleException(e);
        }
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

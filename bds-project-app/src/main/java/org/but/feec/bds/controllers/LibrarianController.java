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
import org.but.feec.bds.api.*;
import org.but.feec.bds.data.*;
import org.but.feec.bds.exceptions.ExceptionHandler;
import org.but.feec.bds.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class LibrarianController {
    private static final Logger logger = LoggerFactory.getLogger(LibrarianController.class);

    @FXML
    public Label usernameLabel;
    @FXML
    private Button signOutButton;
    @FXML
    public Tab physicalBooksTab;
    @FXML
    private TableColumn<PhysicalBookSimpleView, Long> physicalBookIdColumn;
    @FXML
    private TableColumn<PhysicalBookSimpleView, String> physicalBookIsbnColumn;
    @FXML
    private TableColumn<PhysicalBookSimpleView, String> physicalBookTitleColumn;
    @FXML
    private TableColumn<PhysicalBookSimpleView, Boolean> physicalBookLoanedColumn;
    @FXML
    private TableView<PhysicalBookSimpleView> physicalBooksTableView;
    @FXML
    private Button loanBookButton;
    @FXML
    private Button returnBookButton;

    @FXML
    public Tab bookRequestsTab;
    @FXML
    private TableColumn<BookRequestSimpleView, Long> bookRequestIdColumn;
    @FXML
    private TableColumn<BookRequestSimpleView, String> bookRequestTitleColumn;
    @FXML
    private TableColumn<BookRequestSimpleView, String> bookRequestIsbnColumn;
    @FXML
    private TableView<BookRequestSimpleView> bookRequestsTableView;

    @FXML
    public Tab usersTab;
    @FXML
    private TableColumn<StandardUserSimpleView, Long> userIdColumn;
    @FXML
    private TableColumn<StandardUserSimpleView, String> userFirstNameColumn;
    @FXML
    private TableColumn<StandardUserSimpleView, String> userLastNameColumn;
    @FXML
    private TableColumn<StandardUserSimpleView, String> userRoleColumn;
    @FXML
    private TableView<StandardUserSimpleView> usersTableView;

    private SessionService sessionService;
    private UserRepository userRepository;
    private PhysicalBookService physicalBookService;
    private PhysicalBookRepository physicalBookRepository;
    private BookRequestService bookRequestService;
    private BookRequestRepository bookRequestRepository;
    private UserService userService;

    @FXML
    private void initialize() {
        initializeServices();

        usernameLabel.setText(sessionService.getCurrentUsername());

        physicalBookIdColumn.setCellValueFactory(new PropertyValueFactory<PhysicalBookSimpleView, Long>("id"));
        physicalBookIsbnColumn.setCellValueFactory(new PropertyValueFactory<PhysicalBookSimpleView, String>("isbn"));
        physicalBookTitleColumn.setCellValueFactory(new PropertyValueFactory<PhysicalBookSimpleView, String>("title"));
        physicalBookLoanedColumn.setCellValueFactory(new PropertyValueFactory<PhysicalBookSimpleView, Boolean>("loaned"));
        ObservableList<PhysicalBookSimpleView> observablePhysicalBookSimpleViews = initializePhysicalBooksData();
        physicalBooksTableView.setItems(observablePhysicalBookSimpleViews);
        physicalBooksTableView.getSortOrder().add(physicalBookIdColumn);
        initializePhysicalBooksTableViewSelection();

        bookRequestIdColumn.setCellValueFactory(new PropertyValueFactory<BookRequestSimpleView, Long>("id"));
        bookRequestTitleColumn.setCellValueFactory(new PropertyValueFactory<BookRequestSimpleView, String>("title"));
        bookRequestIsbnColumn.setCellValueFactory(new PropertyValueFactory<BookRequestSimpleView, String>("isbn"));
        ObservableList<BookRequestSimpleView> observableBookRequestSimpleViews = initializeBookRequestsData();
        bookRequestsTableView.setItems(observableBookRequestSimpleViews);
        bookRequestsTableView.getSortOrder().add(bookRequestIdColumn);
        initializeBookRequestsTableViewSelection();

        userIdColumn.setCellValueFactory(new PropertyValueFactory<StandardUserSimpleView, Long>("id"));
        userFirstNameColumn.setCellValueFactory(new PropertyValueFactory<StandardUserSimpleView, String>("firstName"));
        userLastNameColumn.setCellValueFactory(new PropertyValueFactory<StandardUserSimpleView, String>("lastName"));
        userRoleColumn.setCellValueFactory(new PropertyValueFactory<StandardUserSimpleView, String>("role"));
        ObservableList<StandardUserSimpleView> observableStandardUserSimpleViews = initializeStandardUsersData();
        usersTableView.setItems(observableStandardUserSimpleViews);
        usersTableView.getSortOrder().add(userIdColumn);
        initializeUsersTableViewSelection();

        logger.info("LibrarianController initialized");
    }

    private void initializePhysicalBooksTableViewSelection() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem detailedView = new MenuItem("Detailed view");
        detailedView.setOnAction((ActionEvent event) -> {
            PhysicalBookSimpleView physicalBookSimpleView = physicalBooksTableView.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/PhysicalBookDetailedView.fxml"));
                Stage stage = new Stage();
                PhysicalBookDetailedView physicalBookDetailedView = physicalBookService.getPhysicalBookDetailedViewById(physicalBookSimpleView.getId());
                stage.setUserData(physicalBookDetailedView);
                stage.setTitle("Physical book detailed view");
                stage.getIcons().add(new Image(App.class.getResourceAsStream("images/lms_logo.png")));
                PhysicalBookDetailedViewController physicalBookDetailedViewController = new PhysicalBookDetailedViewController();
                physicalBookDetailedViewController.setStage(stage);
                fxmlLoader.setController(physicalBookDetailedViewController);
                Scene scene = new Scene(fxmlLoader.load(), 400, 500);
                stage.setScene(scene);
                stage.show();
            }
            catch (IOException e) {
                ExceptionHandler.handleException(e);
            }
        });
        contextMenu.getItems().add(detailedView);

        physicalBooksTableView.setContextMenu(contextMenu);
    }

    private void initializeBookRequestsTableViewSelection() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem editView = new MenuItem("Edit");
        editView.setOnAction((ActionEvent event) -> {
            BookRequestSimpleView bookRequestSimpleView = bookRequestsTableView.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/BookRequestEditView.fxml"));
                Stage stage = new Stage();
                stage.setUserData(bookRequestSimpleView);
                stage.setTitle("Edit book request");
                stage.getIcons().add(new Image(App.class.getResourceAsStream("images/lms_logo.png")));
                BookRequestEditViewController bookRequestEditViewController = new BookRequestEditViewController();
                bookRequestEditViewController.setRefreshCallback(this::refreshBookRequestsTableView);
                bookRequestEditViewController.setStage(stage);
                fxmlLoader.setController(bookRequestEditViewController);
                Scene scene = new Scene(fxmlLoader.load(), 420, 253);
                stage.setScene(scene);
                stage.show();
            }
            catch (IOException e) {
                ExceptionHandler.handleException(e);
            }
        });
        contextMenu.getItems().add(editView);

        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction((ActionEvent event) -> {
            Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION);
            alertDialog.setTitle("Delete book request");
            alertDialog.setHeaderText("You are about to delete book request");
            alertDialog.setContentText("Click OK to proceed");
            Optional<ButtonType> result = alertDialog.showAndWait();

            if (result.get() == ButtonType.OK) {
                bookRequestService.deleteBookRequest(bookRequestsTableView.getSelectionModel().getSelectedItem().getId());
                refreshBookRequestsTableView();
            }
        });
        contextMenu.getItems().add(delete);

        bookRequestsTableView.setContextMenu(contextMenu);
    }

    private void initializeUsersTableViewSelection(){
        ContextMenu contextMenu = new ContextMenu();

        MenuItem detailedView = new MenuItem("Detailed view");
        detailedView.setOnAction((ActionEvent event) -> {
            StandardUserSimpleView standardUserSimpleView = usersTableView.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/StandardUserDetailedView.fxml"));
                Stage stage = new Stage();
                StandardUserDetailedView standardUserDetailedView = userService.getStandardUserDetailedViewById(standardUserSimpleView.getId());
                stage.setUserData(standardUserDetailedView);
                stage.setTitle("User detailed view");
                stage.getIcons().add(new Image(App.class.getResourceAsStream("images/lms_logo.png")));
                StandardUserDetailedViewController standardUserDetailedViewController = new StandardUserDetailedViewController();
                standardUserDetailedViewController.setStage(stage);
                fxmlLoader.setController(standardUserDetailedViewController);
                Scene scene = new Scene(fxmlLoader.load(), 400, 400);
                stage.setScene(scene);
                stage.show();
            }
            catch (IOException e) {
                ExceptionHandler.handleException(e);
            }
        });
        contextMenu.getItems().add(detailedView);

        MenuItem editView = new MenuItem("Edit");
        editView.setOnAction((ActionEvent event) -> {
            StandardUserSimpleView standardUserSimpleView = usersTableView.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/StandardUserEditView.fxml"));
                Stage stage = new Stage();
                StandardUserDetailedView standardUserDetailedView = userService.getStandardUserDetailedViewById(standardUserSimpleView.getId());
                stage.setUserData(standardUserDetailedView);
                stage.setTitle("Edit user");
                stage.getIcons().add(new Image(App.class.getResourceAsStream("images/lms_logo.png")));
                StandardUserEditViewController standardUserEditViewController = new StandardUserEditViewController();
                standardUserEditViewController.setRefreshCallback(this::refreshUsersTableView);
                standardUserEditViewController.setStage(stage);
                fxmlLoader.setController(standardUserEditViewController);
                Scene scene = new Scene(fxmlLoader.load(), 420, 420);
                stage.setScene(scene);
                stage.show();
            }
            catch (IOException e) {
                ExceptionHandler.handleException(e);
            }

        });
        contextMenu.getItems().add(editView);

        usersTableView.setContextMenu(contextMenu);
    }

    private void initializeServices() {
        userRepository = new UserRepository();
        physicalBookRepository = new PhysicalBookRepository();
        bookRequestRepository = new BookRequestRepository();

        sessionService = new SessionService(userRepository, UserSession.getSession());
        physicalBookService = new PhysicalBookService(physicalBookRepository);
        bookRequestService = new BookRequestService(bookRequestRepository);
        userService = new UserService(userRepository);
    }

    private ObservableList<PhysicalBookSimpleView> initializePhysicalBooksData() {
        List<PhysicalBookSimpleView> physicalBookSimpleViews = physicalBookService.getPhysicalBooksSimpleView();
        return FXCollections.observableArrayList(physicalBookSimpleViews);
    }

    private ObservableList<BookRequestSimpleView> initializeBookRequestsData() {
        List<BookRequestSimpleView> bookRequestSimpleViews = bookRequestService.getBookRequestsSimpleView();
        return FXCollections.observableArrayList(bookRequestSimpleViews);
    }

    private ObservableList<StandardUserSimpleView> initializeStandardUsersData() {
        List<StandardUserSimpleView> standardUserSimpleViews = userService.getStandardUsersView();
        return FXCollections.observableArrayList(standardUserSimpleViews);
    }

    @FXML
    public void loanBookActionHandler(ActionEvent event) {
        handleLoanBook();
    }

    private void handleLoanBook() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/LoanBook.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Loan book");
            stage.getIcons().add(new Image(App.class.getResourceAsStream("images/lms_logo.png")));
            LoanBookController loanBookController = new LoanBookController();
            loanBookController.setRefreshCallback(this::refreshPhysicalBooksTableView);
            fxmlLoader.setController(loanBookController);
            Scene scene = new Scene(fxmlLoader.load(), 407, 400);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            ExceptionHandler.handleException(e);
        }
    }

    @FXML
    public void returnBookActionHandler(ActionEvent event) {
        handleReturnBook();
    }

    private void handleReturnBook() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/ReturnBook.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Return book");
            stage.getIcons().add(new Image(App.class.getResourceAsStream("images/lms_logo.png")));
            ReturnBookController returnBookController = new ReturnBookController();
            returnBookController.setRefreshCallback(this::refreshPhysicalBooksTableView);
            fxmlLoader.setController(returnBookController);
            Scene scene = new Scene(fxmlLoader.load(), 407, 462);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            ExceptionHandler.handleException(e);
        }
    }

    public void signOutActionHandler(ActionEvent event) {
        handleSignOut();
    }

    private void handleSignOut(){
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        stage.close();
    }

    public void refreshUsersTableView() {
        usersTableView.setItems(initializeStandardUsersData());
        usersTableView.getSortOrder().clear();
        usersTableView.getSortOrder().add(userIdColumn);
        usersTableView.refresh();
    }

    public void refreshBookRequestsTableView() {
        bookRequestsTableView.setItems(initializeBookRequestsData());
        bookRequestsTableView.getSortOrder().clear();
        bookRequestsTableView.getSortOrder().add(bookRequestIdColumn);
        bookRequestsTableView.refresh();
    }

    public void refreshPhysicalBooksTableView() {
        physicalBooksTableView.setItems(initializePhysicalBooksData());
        physicalBooksTableView.getSortOrder().clear();
        physicalBooksTableView.getSortOrder().add(physicalBookIdColumn);
        physicalBooksTableView.refresh();
    }
}

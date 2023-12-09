package org.but.feec.bds;

import org.but.feec.bds.exceptions.ExceptionHandler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/Login.fxml"));
            primaryStage.setTitle("LMS");
            Scene scene = new Scene(fxmlLoader.load());
            setUserAgentStylesheet(STYLESHEET_MODENA);
            String customStyle = getClass().getResource("css/customStyle.css").toExternalForm();
            scene.getStylesheets().add(customStyle);

            primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("images/lms_logo.png")));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            ExceptionHandler.handleException(ex);
        }
    }
}

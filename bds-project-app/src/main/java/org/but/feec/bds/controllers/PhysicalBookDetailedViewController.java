package org.but.feec.bds.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.but.feec.bds.api.PhysicalBookDetailedView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhysicalBookDetailedViewController {
    private static final Logger logger = LoggerFactory.getLogger(PhysicalBookDetailedViewController.class);

    @FXML
    private Label titleLabel;
    @FXML
    private Label idValueLabel;
    @FXML
    private Label titleValueLabel;
    @FXML
    private Label isbnValueLabel;
    @FXML
    private Label libraryValueLabel;
    @FXML
    private CheckBox loanedCheckBox;
    @FXML
    private Label issueDateValueLabel;
    @FXML
    private Label dueDateValueLabel;
    @FXML
    private TextArea conditionTextArea;
    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        loanedCheckBox.setDisable(true);
        conditionTextArea.setEditable(false);
        loadPhysicalBookData();

        logger.info("PhysicalBookDetailedViewController initialized");
    }

    private void loadPhysicalBookData() {
        Stage stage = this.stage;
        if (stage.getUserData() instanceof PhysicalBookDetailedView physicalBookDetailedView) {
            idValueLabel.setText(String.valueOf(physicalBookDetailedView.getId()));
            titleValueLabel.setText(physicalBookDetailedView.getTitle());
            isbnValueLabel.setText(physicalBookDetailedView.getIsbn());
            libraryValueLabel.setText(physicalBookDetailedView.getLibrary());
            loanedCheckBox.setSelected(physicalBookDetailedView.isLoaned());
            issueDateValueLabel.setText(physicalBookDetailedView.getIssueDate() == null ? "" : physicalBookDetailedView.getIssueDate().toString());
            dueDateValueLabel.setText(physicalBookDetailedView.getDueDate() == null ? "" : physicalBookDetailedView.getDueDate().toString());
            conditionTextArea.setText(physicalBookDetailedView.getCondition());
        }
    }
}

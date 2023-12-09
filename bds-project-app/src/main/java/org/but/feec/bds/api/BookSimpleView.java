package org.but.feec.bds.api;

import javafx.beans.property.*;

import java.math.BigDecimal;

public class BookSimpleView {
    private StringProperty title = new SimpleStringProperty();
    private StringProperty isbn = new SimpleStringProperty();
    private IntegerProperty year = new SimpleIntegerProperty();
    private FloatProperty evaluation = new SimpleFloatProperty();
    private StringProperty language = new SimpleStringProperty();

    public String getTitle() {
        return titleProperty().get();
    }

    public void setTitle(String title) {
        this.titleProperty().setValue(title);
    }


    public String getIsbn() {
        return isbnProperty().get();
    }

    public void setIsbn(String isbn) {
        this.isbnProperty().setValue(isbn);
    }

    public int getYear() {
        return yearProperty().get();
    }

    public void setYear(int year) {
        this.yearProperty().setValue(year);
    }

    public float getEvaluation() {
        return evaluationProperty().get();
    }

    public void setEvaluation(float evaluation) {
        this.evaluationProperty().setValue(evaluation);
    }

    public String getLanguage() {
        return languageProperty().get();
    }

    public void setLanguage(String language) {
        this.languageProperty().setValue(language);
    }

    public StringProperty titleProperty() {
        return title;
    }


    public StringProperty isbnProperty() {
        return isbn;
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public FloatProperty evaluationProperty() {
        return evaluation;
    }

    public StringProperty languageProperty() {
        return language;
    }
}

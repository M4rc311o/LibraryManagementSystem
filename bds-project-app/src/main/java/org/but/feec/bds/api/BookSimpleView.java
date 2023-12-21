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
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public float getEvaluation() {
        return evaluation.get();
    }

    public FloatProperty evaluationProperty() {
        return evaluation;
    }

    public void setEvaluation(float evaluation) {
        this.evaluation.set(evaluation);
    }

    public String getLanguage() {
        return language.get();
    }

    public StringProperty languageProperty() {
        return language;
    }

    public void setLanguage(String language) {
        this.language.set(language);
    }
}

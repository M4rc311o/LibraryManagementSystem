package org.but.feec.bds.api;

import javafx.beans.property.*;

public class BookDetailedView {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty isbn = new SimpleStringProperty();
    private StringProperty title = new SimpleStringProperty();
    private IntegerProperty year = new SimpleIntegerProperty();
    private FloatProperty evaluation = new SimpleFloatProperty();
    private StringProperty genre = new SimpleStringProperty();
    private StringProperty language = new SimpleStringProperty();
    private StringProperty binding = new SimpleStringProperty();
    protected StringProperty literaryPeriod = new SimpleStringProperty();

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
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

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
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

    public String getGenre() {
        return genre.get();
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
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

    public String getBinding() {
        return binding.get();
    }

    public StringProperty bindingProperty() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding.set(binding);
    }

    public String getLiteraryPeriod() {
        return literaryPeriod.get();
    }

    public StringProperty literaryPeriodProperty() {
        return literaryPeriod;
    }

    public void setLiteraryPeriod(String literaryPeriod) {
        this.literaryPeriod.set(literaryPeriod);
    }
}

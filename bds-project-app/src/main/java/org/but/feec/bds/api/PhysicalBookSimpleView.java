package org.but.feec.bds.api;

import javafx.beans.property.*;

public class PhysicalBookSimpleView {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty isbn = new SimpleStringProperty();
    private  StringProperty title = new SimpleStringProperty();
    private BooleanProperty loaned = new SimpleBooleanProperty();

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
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

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public boolean isLoaned() {
        return loaned.get();
    }

    public BooleanProperty loanedProperty() {
        return loaned;
    }

    public void setLoaned(boolean loaned) {
        this.loaned.set(loaned);
    }
}

package org.but.feec.bds.api;

import javafx.beans.property.*;

import java.sql.Date;

public class PhysicalBookDetailedView {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty isbn = new SimpleStringProperty();
    private StringProperty library = new SimpleStringProperty();
    private BooleanProperty loaned = new SimpleBooleanProperty();
    private ObjectProperty<Date> issueDate = new SimpleObjectProperty<>();
    private ObjectProperty<Date> dueDate = new SimpleObjectProperty<>();
    private StringProperty condition = new SimpleStringProperty();

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

    public String getLibrary() {
        return library.get();
    }

    public StringProperty libraryProperty() {
        return library;
    }

    public void setLibrary(String library) {
        this.library.set(library);
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

    public Date getIssueDate() {
        return issueDate.get();
    }

    public ObjectProperty<Date> issueDateProperty() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate.set(issueDate);
    }

    public Date getDueDate() {
        return dueDate.get();
    }

    public ObjectProperty<Date> dueDateProperty() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate.set(dueDate);
    }

    public String getCondition() {
        return condition.get();
    }

    public StringProperty conditionProperty() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition.set(condition);
    }
}

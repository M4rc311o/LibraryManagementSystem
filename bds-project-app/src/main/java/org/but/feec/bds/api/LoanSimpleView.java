package org.but.feec.bds.api;

import javafx.beans.property.*;

import java.sql.Date;

public class LoanSimpleView {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty bookName = new SimpleStringProperty();
    private ObjectProperty<Date> issueDate = new SimpleObjectProperty<>();
    private ObjectProperty<Date> dueDate = new SimpleObjectProperty<>();
    private BooleanProperty returned = new SimpleBooleanProperty();

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getBookName() {
        return bookName.get();
    }

    public StringProperty bookNameProperty() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName.set(bookName);
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

    public boolean isReturned() {
        return returned.get();
    }

    public BooleanProperty returnedProperty() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned.set(returned);
    }
}

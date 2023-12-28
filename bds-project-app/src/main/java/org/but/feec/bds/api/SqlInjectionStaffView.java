package org.but.feec.bds.api;

import javafx.beans.property.*;

import java.sql.Date;

public class SqlInjectionStaffView {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty username = new SimpleStringProperty();
    private ObjectProperty<Date> joinDate = new SimpleObjectProperty<>();
    private StringProperty phoneNumber = new SimpleStringProperty();

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public Date getJoinDate() {
        return joinDate.get();
    }

    public ObjectProperty<Date> joinDateProperty() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate.set(joinDate);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
}

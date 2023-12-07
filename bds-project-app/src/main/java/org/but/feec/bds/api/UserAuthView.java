package org.but.feec.bds.api;

public class UserAuthView {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" + "username='" + username + "', " + "password='" + password + "'}";
    }
}

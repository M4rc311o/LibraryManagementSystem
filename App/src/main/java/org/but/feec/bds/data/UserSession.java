package org.but.feec.bds.data;

public class UserSession {
    private static UserSession session = null;
    private long userId;
    private String username;
    private String role;

    private UserSession() {}

    public static synchronized  UserSession getSession() {
        if (session == null) {
            session = new UserSession();
        }
        return session;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return  role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

package com.vms.session;

import com.vms.model.User;

public class AppSession {

    private static AppSession instance;

    private User loggedInUser;

    private AppSession() {}

    public static AppSession getInstance() {

        if(instance == null) {
            instance = new AppSession();
        }
        return instance;
    }

    public void setUser(User user) {
        this.loggedInUser = user;
    }

    public User getUser() {
        return loggedInUser;
    }

    public void clear() {
        loggedInUser = null;
    }
}

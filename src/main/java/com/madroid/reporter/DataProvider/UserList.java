package com.madroid.reporter.DataProvider;

import java.util.List;

public class UserList {
    List<User> userList;

    public UserList() {
    }

    public UserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}

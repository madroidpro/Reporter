package com.madroid.reporter.service;

import com.madroid.reporter.DataProvider.DataProviderService;
import com.madroid.reporter.DataProvider.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserDataController {

    @Autowired
    private DataProviderService service;

    @GetMapping("/getAllUsers")
    public UserList getUserData() {
        UserList userList = new UserList(service.findAll());
        return userList;
    }
}

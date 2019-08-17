package com.ot.springboot.uitest.services;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean validateUser(String userid, String password) {
        // in28minutes, dummy
        return userid.equalsIgnoreCase("admin")
                && password.equalsIgnoreCase("admin");
    }

}
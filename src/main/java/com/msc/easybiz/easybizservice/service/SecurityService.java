package com.msc.easybiz.easybizservice.service;

import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    public String authenticate(String username, String password){
        return username + ":" + password;
    }
}

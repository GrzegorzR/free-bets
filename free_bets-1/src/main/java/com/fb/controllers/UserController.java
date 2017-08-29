package com.fb.controllers;

import com.fb.models.BetUser;
import com.fb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/is-logged")
    public ResponseEntity isLogged(){
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/logout-success")
    public ResponseEntity logoutSuccesMessage(){
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/user/current")
    public ResponseEntity getCurrentUser(){
        try{
            BetUser currentUser = userService.getCurrentUser();
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

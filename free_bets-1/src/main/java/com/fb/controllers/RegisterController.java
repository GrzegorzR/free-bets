package com.fb.controllers;


import com.fb.dto.UserDTO;
import com.fb.models.BetUser;
import com.fb.services.UserService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/registration")
    public ResponseEntity registerUserAccount (@NotEmpty String login, @NotEmpty String password){

        try {
            System.out.println("invoke");
            userService.addNewUser(login, password);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("{\"success\": \"true\"}",HttpStatus.OK);
    }

}

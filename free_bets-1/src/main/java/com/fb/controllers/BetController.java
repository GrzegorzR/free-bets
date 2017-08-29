package com.fb.controllers;


import com.fb.models.Bet;
import com.fb.services.BetDefinitionService;
import com.fb.services.BetService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BetController {

    @Autowired
    public BetService betService;


    @RequestMapping(value = "/bet/add")
    public ResponseEntity addBet(@NotEmpty Long betDefinitionId, @NotEmpty Long option, @NotEmpty Double amount){
        try {
            betService.addBet(betDefinitionId, option, amount);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @RequestMapping(value = "/bet/my")
    public ResponseEntity getCurentUsersBets(){
        try {
            List<Bet> bets = betService.getCurrentUserBets();
            return new ResponseEntity(bets,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }






}

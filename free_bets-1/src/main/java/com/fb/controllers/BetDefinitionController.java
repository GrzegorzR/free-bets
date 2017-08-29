package com.fb.controllers;


import com.fb.models.BetDefinition;
import com.fb.repositories.BetDefinitionRepository;
import com.fb.services.BetDefinitionService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BetDefinitionController {

    @Autowired
    public BetDefinitionService betDefinitionService;
    @Autowired
    public BetDefinitionRepository betDefinitionRepository;

    @RequestMapping(value = "/bet-definition/add")
    public ResponseEntity addBetDefinition(String description, String optionOne, String optionTwo){
        try {
            betDefinitionService.addBetDefinition(description, optionOne, optionTwo);
             return new ResponseEntity<>("{\"success\": \"true\"}", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/bet-definition/get-active")
    public ResponseEntity getActiveBetDef(){
        try {
            List<BetDefinition> activeBets = betDefinitionService.getActiveBets();
            return new ResponseEntity<>(activeBets, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/bet-definition/get-inactive")
    public ResponseEntity getInactiveBetDef(){
        try {
            List<BetDefinition> activeBets = betDefinitionRepository.findByResolved(true);
            return  new ResponseEntity(activeBets, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/bet-definition/resolve")
    public ResponseEntity resolveBet(@NotEmpty Long betDefinitionId, @NotEmpty Long option){

        try {
            betDefinitionService.resolveBetDefinition(betDefinitionId, option);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/bet-definition/my")
    public ResponseEntity getCurrentUsersBetDefefinitions(){
        try {
            List<BetDefinition> result = betDefinitionService.getCurrentUsersBetDefinitions();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

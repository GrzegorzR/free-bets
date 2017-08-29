package com.fb.services;


import com.fb.models.Bet;
import com.fb.models.BetDefinition;
import com.fb.models.BetUser;
import com.fb.repositories.BetDefinitionRepository;
import com.fb.repositories.BetRepository;
import com.fb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class BetService {


    @Autowired
    public UserService userService;

    @Autowired
    public BetDefinitionRepository betDefinitionRepository;

    @Autowired
    public BetRepository betRepository;
    @Autowired
    public UserRepository userRepository;


    @Transactional
    public void addBet(Long betDefinitionId, Long option, Double amount) throws Exception {
        BetUser currentUser = userService.getCurrentUser();
        BetDefinition betDefinition = betDefinitionRepository.findById(betDefinitionId);
        Bet bet = new Bet(currentUser, option, betDefinition, amount);

        validateNewBet(bet, betDefinition, currentUser);
        currentUser.setMoney(currentUser.getMoney() - amount);
        userRepository.save(currentUser);
        betRepository.save(bet);
    }

    private void validateNewBet(Bet bet, BetDefinition betDefinition, BetUser currentUser) throws Exception {
        if(currentUser.getId() == betDefinition.getOwner().getId()){
            throw new Exception("You can't take your own bet.");
        }
        if(currentUser.getMoney() < bet.getAmount()){
            throw new Exception("You haven't enough money.");
        }
    }

    @Transactional
    public List<Bet> getCurrentUserBets(){
        BetUser currentUser = userService.getCurrentUser();
        List<Bet> result = betRepository.findByBetUser(currentUser);
        return  result;
    }
}

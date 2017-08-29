package com.fb.services;


import com.fb.models.Bet;
import com.fb.models.BetDefinition;
import com.fb.models.BetUser;
import com.fb.repositories.BetDefinitionRepository;
import com.fb.repositories.BetRepository;
import com.fb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class BetDefinitionService {

    @Autowired
    public UserService userService;
    @Autowired
    public BetDefinitionRepository betDefinitionRepository;
    @Autowired
    public BetRepository betRepository;
    @Autowired
    public UserRepository userRepository;

    public void addBetDefinition(String description, String optionOne, String optionTwo){
        BetUser user = userService.getCurrentUser();

        BetDefinition betDefinition = new BetDefinition(user,description,optionOne,
                                                        optionTwo,false,1);
        betDefinitionRepository.save(betDefinition);
    }

    @Transactional
    public void resolveBetDefinition(Long betDefinitionId, Long option) throws Exception {
        BetDefinition betDefinition = betDefinitionRepository.findById(betDefinitionId);

        validateBetResolving(betDefinition);

        List<Bet> matchingBets = betRepository.findByBetDefinition(betDefinition);

        List<Bet> winingBets = matchingBets.stream().filter(bet -> bet.getOption() == option)
                .collect(Collectors.toList());
        List<Bet> losingBets = matchingBets.stream().filter(bet -> bet.getOption() != option)
                .collect(Collectors.toList());

        Double winingAmount = winingBets
                .stream()
                .mapToDouble(Bet::getAmount)
                .sum();

        Double losingAmount = losingBets
                .stream()
                .mapToDouble(Bet::getAmount)
                .sum();

        Double fullAmount = winingAmount+losingAmount;


        updateOwnerMoney(betDefinition, fullAmount);
        updateWinnersMoney(winingBets, winingAmount, losingAmount);
        betDefinition.setResolved(true);
        betDefinition.setWinOption(Math.toIntExact(option));
        betDefinitionRepository.save(betDefinition);

    }

    private void updateOwnerMoney(BetDefinition betDefinition, Double fullAmount) {
        Double betOwnerFee = fullAmount * 0.001;

        BetUser owner = betDefinition.getOwner();

        Double updatedOwnersMoney = owner.getMoney() + betOwnerFee;
        owner.setMoney(updatedOwnersMoney);
        userRepository.save(owner);
    }

    private void updateWinnersMoney(List<Bet> winingBets, Double winingAmount, Double losingAmount) {
        Double losingAmountAfterFee = losingAmount*0.999;
        Double winingAmountAfterFee = winingAmount*0.999;

        for (Bet bet : winingBets) {
            BetUser user = bet.getBetUser();

            Double winingImpact = bet.getAmount()/winingAmount;
            Double newMoneyAmount = user.getMoney() + winingImpact*winingAmountAfterFee + winingImpact*losingAmountAfterFee;

            user.setMoney(newMoneyAmount);

            userRepository.save(user);
        }
    }

    private void validateBetResolving(BetDefinition betDefinition) throws Exception {
        BetUser user = userService.getCurrentUser();
        if(betDefinition.getOwner().getId() != user.getId()){
            throw new Exception("It's not your bet.");
        }

    }

    @Transactional
    public List<BetDefinition> getCurrentUsersBetDefinitions() {
        BetUser currentUser = userService.getCurrentUser();
        List<BetDefinition> result = betDefinitionRepository.findByOwnerAndResolved(currentUser, false);
        return result;
    }

    @Transactional
    public List<BetDefinition> getActiveBets() {
        List<BetDefinition> acitveBetsDefs = betDefinitionRepository.findByResolved(false);
        BetUser currentUser = userService.getCurrentUser();
        List<BetDefinition> result = acitveBetsDefs.stream().filter(betDef -> betDef.getOwner() != currentUser)
                .collect(Collectors.toList());
        return result;
    }
}

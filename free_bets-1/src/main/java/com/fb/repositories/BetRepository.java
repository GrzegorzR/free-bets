package com.fb.repositories;


import com.fb.models.Bet;
import com.fb.models.BetDefinition;
import com.fb.models.BetUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BetRepository extends CrudRepository<Bet, Long> {
    List<Bet> findByBetDefinition(BetDefinition betDefinition);

    List<Bet> findByBetUser(BetUser betUser);
}

package com.fb.repositories;


import com.fb.models.BetDefinition;
import com.fb.models.BetUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BetDefinitionRepository  extends CrudRepository<BetDefinition, Long> {

    List<BetDefinition> findAll();

    BetDefinition findById(Long betDefinitionId);

    List<BetDefinition> findByResolved(boolean b);

    List<BetDefinition> findByOwner(BetUser currentUser);

    List<BetDefinition> findByOwnerAndResolved(BetUser currentUser, boolean b);
}

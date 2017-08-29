package com.fb.repositories;


import com.fb.models.BetUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<BetUser, Long> {

    List<BetUser> findByLogin(String login) ;
}

package com.fb;

import com.fb.services.BetDefinitionService;
import com.fb.services.BetService;
import com.fb.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Bean
    public UserService clientRepository(){return new UserService();}

    @Bean
    public BetService betService(){return new BetService();}

    @Bean
    public BetDefinitionService betDefinitionService() {return new BetDefinitionService();}

}

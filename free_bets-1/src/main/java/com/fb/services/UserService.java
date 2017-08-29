package com.fb.services;


import com.fb.dto.UserDTO;
import com.fb.models.BetUser;
import com.fb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserRepository userRepository;

    public void addNewUser(String login, String password){
        UserDTO userDTO = new UserDTO(login, password);
        System.out.println(userDTO.getLogin());
        BetUser newUser = new BetUser(userDTO.getLogin(),
                passwordEncoder.encode(userDTO.getPassword()),
                "USER",
                100d);
        userRepository.save(newUser);
    }


    public BetUser getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        BetUser user = userRepository.findByLogin(login).get(0);
        return user;
    }
}

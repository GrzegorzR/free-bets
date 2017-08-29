package com.fb.security;


import com.fb.models.BetUser;
import com.fb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class AuthProvider extends AbstractUserDetailsAuthenticationProvider {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserRepository userRepository;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UserDetails loadedUser;
        try {
            String rawPassword = authentication.getCredentials().toString();
            BetUser usr = userRepository.findByLogin(username).get(0);
            System.out.println(usr.getLogin());
            System.out.println(rawPassword);
            if (!passwordEncoder.matches(rawPassword, usr.getPassword()))
                throw new AuthenticationServiceException("Wrong password");
            loadedUser = new User(usr.getLogin(), usr.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_" + usr.getRole())));
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }
        return loadedUser;
    }
}

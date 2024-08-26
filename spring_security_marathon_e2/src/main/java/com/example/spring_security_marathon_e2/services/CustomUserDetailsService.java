package com.example.spring_security_marathon_e2.services;

import com.example.spring_security_marathon_e2.entities.User;
import com.example.spring_security_marathon_e2.exceptions.UserAlreadyExistsException;
import com.example.spring_security_marathon_e2.models.security.SecurityUser;
import com.example.spring_security_marathon_e2.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("In USERDETAILS");
        var u = userRepository.findUserByUsername(username);

        User user = u.orElseThrow(() -> new UsernameNotFoundException("username not found"));

        return new SecurityUser(user);

    }

    @Transactional
    public void createUser(User user){
        var u = userRepository.findUserByUsername(user.getUsername());

        if (u.isPresent()){
            throw new UserAlreadyExistsException("username already exists!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

    }
}

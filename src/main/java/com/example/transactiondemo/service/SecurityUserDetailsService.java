package com.example.transactiondemo.service;

import com.example.transactiondemo.dto.Authority;
import com.example.transactiondemo.dto.SecurityUserDetails;
import com.example.transactiondemo.entity.User;
import com.example.transactiondemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUserIdUserId(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

        Authority authority = new Authority();
        authority.setId(1);
        authority.setRole("USER");
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);

        return new SecurityUserDetails(optionalUser.get(), authorities);
    }
}

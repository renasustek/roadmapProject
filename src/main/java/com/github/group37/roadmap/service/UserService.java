package com.github.group37.roadmap.service;

import com.github.group37.roadmap.other.UserRequest;
import com.github.group37.roadmap.percistance.models.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final JdbcUserDetailsManager jdbcUserDetailsManager;
    private final PasswordEncoder passwordEncoder;



    public UserService(JdbcUserDetailsManager jdbcUserDetailsManager, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
    }

    public void create(UserRequest userRequest) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        User user = new User(userRequest.name(), userRequest.password(), authorities);
        jdbcUserDetailsManager.createUser(user);

    }



}

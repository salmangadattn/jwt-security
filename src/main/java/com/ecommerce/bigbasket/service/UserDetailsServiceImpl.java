package com.ecommerce.bigbasket.service;

import com.ecommerce.bigbasket.entity.Role;
import com.ecommerce.bigbasket.entity.User;
import com.ecommerce.bigbasket.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userAuthentication = getUserByEmail(username);

        return new org.springframework.security.core.userdetails.User(userAuthentication.getEmail(),
                userAuthentication.getPassword(),
                true,
                true,
                true,
                true,
                getAuthority(userAuthentication.getRoles()));
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with email : " + email));
    }

    private Collection<? extends GrantedAuthority> getAuthority(Set<Role> roles) {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName().toString()));
        }
        return authorities;
    }
}

package com.todo.backend.config;

import com.todo.backend.entity.User;
import com.todo.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        String username = null;
        String password;
        List<GrantedAuthority> grantedAuthorityList = null;
        User user = userRepository.findByEmail(emailId);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        } else {
            username = user.getEmail();
            password = user.getPassword();
            grantedAuthorityList = new ArrayList<>();
            grantedAuthorityList.add(new SimpleGrantedAuthority("USER"));

        }
        return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorityList);
    }
}

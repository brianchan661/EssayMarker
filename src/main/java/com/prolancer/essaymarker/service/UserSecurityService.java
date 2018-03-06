package com.prolancer.essaymarker.service;

import com.prolancer.essaymarker.db.model.UserInfo;
import com.prolancer.essaymarker.db.repository.UserInfoRepository;
import com.prolancer.essaymarker.type.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserInfo userInfo = userInfoRepository.findByEmail(email);
        if (userInfo == null) {
            throw new UsernameNotFoundException("No user found with username: " + email);
        }
        return new User(userInfo.getEmail(), userInfo.getPassword(),
                true, true, true,
                true, getAuthorities(userInfo.getRole()));

    }

    private static List<GrantedAuthority> getAuthorities (Role role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return authorities;
    }
}

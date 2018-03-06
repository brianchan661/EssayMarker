package com.prolancer.essaymarker.service;

import com.prolancer.essaymarker.db.model.UserInfo;
import com.prolancer.essaymarker.db.repository.UserInfoRepository;
import com.prolancer.essaymarker.type.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class UserSecurityService// {
{
//    private UserInfoRepository userInfoRepository;
//
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        UserInfo userInfo = userInfoRepository.findByEmail(email);
//        if (userInfo == null) {
//            throw new UsernameNotFoundException("No user found with username: " + email);
//        }
//        return new User(userInfo.getEmail(), userInfo.getPassword(),
//                true, true, true,
//                true, getAuthorities(userInfo.getRole()));
//
//    }
//
//    private static List<GrantedAuthority> getAuthorities (Role role) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(role.toString()));
//        return authorities;
//    }
}

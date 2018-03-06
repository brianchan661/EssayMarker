package com.prolancer.essaymarker.service;

import com.prolancer.essaymarker.db.model.UserInfo;
import com.prolancer.essaymarker.db.repository.UserInfoRepository;
import com.prolancer.essaymarker.model.view.SignUp;
import com.prolancer.essaymarker.type.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Transactional
    public UserInfo createNewUser(SignUp signUp) {
        UserInfo userInfo = userInfoRepository.findByEmail(signUp.getEmail());
        // return null if email already exist
        if (userInfo != null) {
            return null;
        }
        UserInfo newUser = new UserInfo();
        newUser.setEmail(signUp.getEmail());
        newUser.setRole(Role.USER);
        newUser.setPassword(signUp.getPassword());
        newUser.setCountryCode(signUp.getCountry());
        newUser.setGender(signUp.getGender());
        newUser.setDateOfBirth(signUp.getBday());
        newUser.setUpdateTime(new Date());
        newUser.setCreateTime(new Date());
        userInfoRepository.save(newUser);
        return newUser;
    }

}

package com.prolancer.essaymarker.db.repository;

import com.prolancer.essaymarker.db.model.UserInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {

    UserInfo findByEmail(String email);

    UserInfo findByUserId(int userId);

    @Query("select userInfo from UserInfo userInfo where userInfo.email = :email and userInfo.countryCode = :country")
    List<UserInfo> findByEmailAndCountry(@Param("email") String email, @Param("country") String country);

}

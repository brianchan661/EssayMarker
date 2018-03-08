package com.prolancer.essaymarker.db.repository;

import com.prolancer.essaymarker.db.model.UserInfo;
import com.prolancer.essaymarker.db.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(UserInfo user);
}

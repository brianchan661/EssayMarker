package com.prolancer.essaymarker.db.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
public class VerificationToken {
    private static final int EXPIRATION_MINUTES = 60;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = UserInfo.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserInfo user;

    private Date expiryDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant((new Date()).toInstant(), ZoneId.systemDefault());
        Date expTime = Date.from(localDateTime.plusMinutes(EXPIRATION_MINUTES).atZone(ZoneId.systemDefault()).toInstant());
        return expTime;
    }

}
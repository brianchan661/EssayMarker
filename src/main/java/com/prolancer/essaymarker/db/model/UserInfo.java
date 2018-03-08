package com.prolancer.essaymarker.db.model;

import com.prolancer.essaymarker.type.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class UserInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int userId;
    private String email;
    private Role role;
    private boolean enable;
    private String password;
    private String countryCode;
    private String gender;
    private String dateOfBirth;
    private Date updateTime;
    private Date createTime;
}

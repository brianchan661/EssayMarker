package com.prolancer.essaymarker.model.view;

import com.prolancer.essaymarker.validation.EmailAddress;
import com.prolancer.essaymarker.validation.PasswordConfirm;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
@PasswordConfirm
public class SignUp {

    @NotNull
    @NotEmpty
    @EmailAddress
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String repwd;

    @NotNull
    @NotEmpty
    private String country;

    @NotNull
    @NotEmpty
    private String gender;

    @NotNull
    @NotEmpty
    private String bday;
}

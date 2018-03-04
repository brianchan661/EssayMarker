package com.prolancer.essaymarker.validation.validator;

import com.prolancer.essaymarker.model.view.SignUp;
import com.prolancer.essaymarker.validation.PasswordConfirm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmValidator
        implements ConstraintValidator<PasswordConfirm, Object> {

    @Override
    public void initialize(PasswordConfirm constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        SignUp user = (SignUp) obj;
        return user.getPassword().equals(user.getRepwd());
    }
}
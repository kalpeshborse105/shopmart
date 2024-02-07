package com.shope.shopmart.validators;

import org.springframework.beans.BeanWrapperImpl;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class VerifyPasswordValidator
implements ConstraintValidator<VerifyPassword,Object>{
    private String field;
    private String matchingField;

    @Override
    public void initialize(VerifyPassword constraintAnnotaion) {
        this.field = constraintAnnotaion.field();
        this.matchingField = constraintAnnotaion.matchingField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object matchingFieldValue = new BeanWrapperImpl(value).getPropertyValue(matchingField);
        if(fieldValue != null){
            return fieldValue.equals(matchingFieldValue);
        }
        return matchingFieldValue != null;
    }
}
 
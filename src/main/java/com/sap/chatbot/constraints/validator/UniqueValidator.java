package com.sap.chatbot.constraints.validator;

import com.sap.chatbot.constraints.Unique;
import com.sap.chatbot.forms.EmployeeCreationForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Florin-Gabriel Barbuceanu, florin.barbuceanu@sap.com
 * @since 23/05/2018
 */
public class UniqueValidator implements ConstraintValidator<Unique, EmployeeCreationForm> {

  private Unique uniqueConstraint;

  @Override
  public void initialize(Unique constraintAnnotation) {
    this.uniqueConstraint = constraintAnnotation;
  }

  @Override
  public boolean isValid(EmployeeCreationForm value, ConstraintValidatorContext context) {
    return value.getName().equalsIgnoreCase(uniqueConstraint.name());
  }
}

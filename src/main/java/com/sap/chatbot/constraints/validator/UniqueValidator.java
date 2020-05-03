package com.sap.chatbot.constraints.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sap.chatbot.constraints.Unique;
import com.sap.chatbot.forms.EmployeeCreationForm;

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
    return !uniqueConstraint.value().equalsIgnoreCase(value.getName());
  }
}

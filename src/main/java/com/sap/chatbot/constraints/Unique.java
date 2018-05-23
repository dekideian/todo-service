package com.sap.chatbot.constraints;

import com.sap.chatbot.constraints.validator.UniqueValidator;
import org.springframework.core.annotation.AliasFor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Florin-Gabriel Barbuceanu, florin.barbuceanu@sap.com
 * @since 23/05/2018
 */
@Documented
@Constraint(validatedBy = UniqueValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
  String message() default "There can be only one.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  @AliasFor("value")
  String name() default "";

  @AliasFor("name")
  String value() default "";
}

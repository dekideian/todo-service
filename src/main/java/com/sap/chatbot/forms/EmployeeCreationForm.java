package com.sap.chatbot.forms;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author Florin-Gabriel Barbuceanu, florin.barbuceanu@sap.com
 * @since 23/05/2018
 */
public class EmployeeCreationForm {
  private final String name;
  private final Long age;

  @JsonCreator
  public EmployeeCreationForm(String name, Long age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public Long getAge() {
    return age;
  }
}

package com.sap.chatbot.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.chatbot.constraints.Unique;

/**
 * @author Florin-Gabriel Barbuceanu, florin.barbuceanu@sap.com
 * @since 23/05/2018
 */
@Unique("TheDude")
public class EmployeeCreationForm {
  @JsonProperty("name") private String name;
  @JsonProperty("age") private Long age;

  public String getName() {
    return name;
  }

  public Long getAge() {
    return age;
  }

  public EmployeeCreationForm setName(String name) {
    this.name = name;
    return this;
  }

  public EmployeeCreationForm setAge(Long age) {
    this.age = age;
    return this;
  }

  @Override
  public String toString() {
    return "EmployeeCreationForm{" +
      "name='" + name + '\'' +
      ", age=" + age +
      '}';
  }
}

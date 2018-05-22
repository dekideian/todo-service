package com.sap.chatbot.domain.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author Florin-Gabriel Barbuceanu, florin.barbuceanu@sap.com
 * @since 22/05/2018
 */
@Entity
@Table(schema = "chatbot", name = "employee")
public class Employee {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(nullable = false, updatable = false)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(updatable = false)
  private Long age;

  public UUID getId() {
    return id;
  }

  public Employee setId(UUID id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Employee setName(String name) {
    this.name = name;
    return this;
  }

  public Long getAge() {
    return age;
  }

  public Employee setAge(Long age) {
    this.age = age;
    return this;
  }

  Employee() {}

  private Employee(String name, Long age) {
    this.name = name;
    this.age = age;
  }

  public static Employee of(String name, Long age) {
    return new Employee(name, age);
  }
}

package com.sap.chatbot.repository.api;

import com.sap.chatbot.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

/**
 * @author Florin-Gabriel Barbuceanu, florin.barbuceanu@sap.com
 * @since 22/05/2018
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
  @Query("select e from Employee e where e.age > :age")
  Stream<Employee> findEmployeesOlderThan(@Param("age") Long age);
}

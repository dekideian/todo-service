package com.sap.chatbot.testing;

import com.sap.chatbot.web.controller.EmployeeController;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.sap.chatbot.ChatbotStarterService;
import com.sap.chatbot.domain.entities.Employee;
import com.sap.chatbot.forms.EmployeeCreationForm;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChatbotStarterService.class)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StarterTests {

  @Autowired
  WebTestClient client;

  @Autowired
  EmployeeController controller;


  List<Tuple2<String, Long>> testData =
      List.of(Tuple.of("Tester Dude", 1337L), Tuple.of("John Doe", 25L),
          Tuple.of("Flux User", 43L));

  @Test
  public void test1_Create() {
    List.of(getEmployee(0), getEmployee(1)).forEach((e) -> {
      client.post().uri("/employee").contentType(MediaType.APPLICATION_JSON_UTF8)
          .accept(MediaType.APPLICATION_JSON_UTF8).body(Mono.just(e), EmployeeCreationForm.class)
          .exchange().expectStatus().isOk().expectHeader()
          .contentType(MediaType.APPLICATION_JSON_UTF8).expectBody().jsonPath("$.name").isNotEmpty()
          .jsonPath("$.name").isEqualTo(e.getName()).jsonPath("$.age").isNotEmpty()
          .jsonPath("$.age").isEqualTo(e.getAge());
    });
  }

  @Test
  public void test2_FindByAge() {
    client.get().uri("/employee?olderThan=" + 20).accept(MediaType.APPLICATION_JSON_UTF8).exchange()
        .expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBodyList(Employee.class).hasSize(2);

    client.get().uri("/employee?olderThan=" + 200).accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange().expectStatus().isOk().expectHeader()
        .contentType(MediaType.APPLICATION_JSON_UTF8).expectBodyList(Employee.class).hasSize(1);

    client.get().uri("/employee?olderThan=" + 2000).accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange().expectStatus().isOk().expectHeader()
        .contentType(MediaType.APPLICATION_JSON_UTF8).expectBodyList(Employee.class).hasSize(0);

  }

  @Test
  public void test3_FunctionalEndPointTest() {

    List.of(getEmployee(2)).forEach((e) -> {
      WebTestClient.bindToRouterFunction(controller.createOneWebFlux()).build()
          .post().uri("employee-webflux").contentType(MediaType.APPLICATION_JSON_UTF8)
          .accept(MediaType.APPLICATION_JSON).body(Mono.just(e), EmployeeCreationForm.class)
          .exchange().expectStatus().isOk().expectHeader()
          .contentType(MediaType.APPLICATION_JSON).expectBody().jsonPath("$.name").isNotEmpty()
          .jsonPath("$.name").isEqualTo(e.getName()).jsonPath("$.age").isNotEmpty()
          .jsonPath("$.age").isEqualTo(e.getAge());
    });
  }

  private EmployeeCreationForm getEmployee(int index) {
    return new EmployeeCreationForm().setName(testData.get(index)._1())
        .setAge(testData.get(index)._2());
  }
}

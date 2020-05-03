package com.sap.chatbot.web.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sap.chatbot.domain.entities.todo.Todo;
import com.sap.chatbot.forms.TodoForm;
import com.sap.chatbot.service.api.TodoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
@RestController
public class TodoController {
	
  private final TodoService todoService;

  @Autowired
  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }
  
  @GetMapping(path="/health")
  public String health() {
	  return "Okaa!";
  }

  @GetMapping(path = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<Todo> findAll() {
    return todoService.findAll();
  }
  
  @GetMapping(path = "/todos/active", produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<Todo> findAllActive() {
    return todoService.findActive();
  }

  @GetMapping(path = "/todos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Todo> findOne(@PathVariable("id") UUID id) {
    return todoService.findOne(id);
  }

  @DeleteMapping(path = "/todos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> deleteOne(@PathVariable("id") UUID id) {
    todoService.delete(id);
    return ResponseEntity.ok(null);
  }
  
  @PutMapping(path = "/todos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Todo> updateOne(@PathVariable("id") UUID id,  @RequestBody Todo requestBody) {
    return todoService.update(requestBody);
  }

  
  @PostMapping(path = "/todos", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Todo> createOne(@RequestBody Mono<TodoForm> requestBody) {
    return createOneHandler(requestBody);
  }

  private Mono<Todo> createOneHandler(Mono<TodoForm> requestBody) {
    return requestBody.flatMap(todoForm -> todoService
        .create(todoForm));
  }
}

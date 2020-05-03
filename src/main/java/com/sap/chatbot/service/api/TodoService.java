package com.sap.chatbot.service.api;

import java.util.UUID;

import com.sap.chatbot.domain.entities.todo.Todo;
import com.sap.chatbot.forms.TodoForm;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoService {

	Flux<Todo> findAll();
	
	Flux<Todo> findActive();
	
	Mono<Todo> findOne(UUID id);
	
	Mono<Todo> create(TodoForm todo);
	
	Mono<Todo> update(Todo todo);
	
    void delete(UUID id);
}

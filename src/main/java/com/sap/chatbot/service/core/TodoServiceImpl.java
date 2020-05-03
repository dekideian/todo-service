package com.sap.chatbot.service.core;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.chatbot.async.AsyncRunner;
import com.sap.chatbot.domain.entities.todo.Todo;
import com.sap.chatbot.forms.TodoForm;
import com.sap.chatbot.repository.api.sync.TodoRepository;
import com.sap.chatbot.service.api.TodoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TodoServiceImpl implements TodoService{

	private final AsyncRunner asyncRunner;
	private final TodoRepository todoRepository;
	
	@Autowired
	public TodoServiceImpl(AsyncRunner asyncRunner, TodoRepository todoRepository) {
		super();
		this.asyncRunner = asyncRunner;
		this.todoRepository = todoRepository;
	}
	@Override
	public Flux<Todo> findAll() {
		
		return asyncRunner.many(()->todoRepository.findAll());
	}

	@Override
	public Flux<Todo> findActive() {
		return asyncRunner.many(()->todoRepository.findAllActive());
	}

	@Override
	public Mono<Todo> findOne(UUID id) {
		
		return asyncRunner.oneOrZero(()->todoRepository.findById(id));
	}

	@Override
	public Mono<Todo> create(TodoForm todoForm) {
		
		return asyncRunner.one(()->todoRepository.saveAndFlush(Todo.of(todoForm)));
	}

	@Override
	public Mono<Todo> update(Todo todo) {

		return asyncRunner.one(()->todoRepository.saveAndFlush(todo));
	}

	@Override
	public void delete(UUID id) {
		todoRepository.deleteById(id);
	}

}

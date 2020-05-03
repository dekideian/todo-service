package com.sap.chatbot.repository.api.sync;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sap.chatbot.domain.entities.todo.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID>{

	@Query("SELECT u FROM Todo u WHERE u.active = true")
	Collection<Todo> findAllActive();
}

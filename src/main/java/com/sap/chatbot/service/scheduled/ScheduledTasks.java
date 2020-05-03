package com.sap.chatbot.service.scheduled;

import java.time.LocalDate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sap.chatbot.repository.api.sync.TodoRepository;
import com.sap.chatbot.utils.DateUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledTasks {
	
	private final TodoRepository todoRepository;
	
	public ScheduledTasks(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	@Scheduled(cron = "0 0 0 * * *",zone = "Europe/Bucharest")
	public void reportCurrentTime() {
		//get all tasks, check date, each that belongs to last week, make inactive
		log.info("Running scheduled task at: "+LocalDate.now());
		log.info("CurrentWeekNr: "+DateUtil.getWeekOfYear());
		todoRepository.findAll()
			.stream()
			.forEach(todo->{
				Boolean shouldBeActive = todo.getWeekNr()==DateUtil.getWeekOfYear();
				if(todo.getActive()!=shouldBeActive) {
					todo.setActive(shouldBeActive);
					todoRepository.saveAndFlush(todo);
				}
			});
	}
}

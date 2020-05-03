package com.sap.chatbot.domain.entities.todo;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.sap.chatbot.domain.entities.enums.Category;
import com.sap.chatbot.domain.entities.enums.Reccurence;
import com.sap.chatbot.forms.TodoForm;
import com.sap.chatbot.utils.DateUtil;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Table(schema = "todos", name = "Todo")
public class Todo {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(nullable = false, updatable = false)
	private UUID id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String goal;

	@Column
	private Category category;

	@Column(nullable = false)
	private Reccurence reccurernce;

	@Column
	private Boolean achieved;

	@Column(nullable = false)
	private int weekNr;

	@Column
	private Boolean active;

	@Column
	private Float percentage;
	
	@Column(name = "created_at")
    private LocalDateTime createdAt;

	@Embedded
	private TaskCompletion taskCompletion;
	
	@PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
 
	public static Todo of(TodoForm todoForm) {
		return new Todo(UUID.randomUUID(), todoForm.getName(), todoForm.getGoal(),
				Category.get(todoForm.getCategory()), Reccurence.get(todoForm.getReccurernce()), false,
				getIntendedWeekOfYearForTodo(todoForm), true, new Float("0"), TaskCompletion.emptyProgress());
	}

	private static int getIntendedWeekOfYearForTodo(TodoForm todoForm) {
		return DateUtil.getWeekOfYear()+todoForm.getWeekNr();
	}
	
	public static void main(String[] args) {
		System.out.println("Nurse!");
		System.out.println("week:"+Calendar.WEEK_OF_YEAR);
		
	}

	public Todo(UUID id, String name, String goal, Category category, Reccurence reccurernce, Boolean achieved,
			int weekNr, Boolean active, Float percentage, TaskCompletion taskCompletion) {
		super();
		this.id = id;
		this.name = name;
		this.goal = goal;
		this.category = category;
		this.reccurernce = reccurernce;
		this.achieved = achieved;
		this.weekNr = weekNr;
		this.active = active;
		this.percentage = percentage;
		this.taskCompletion = taskCompletion;
	}
}

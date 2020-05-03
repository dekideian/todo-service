package com.sap.chatbot.domain.entities.todo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TaskCompletion {

	@Column(nullable = false)
	private Boolean mondayCompleted;
	
	@Column(nullable = false)
	private Boolean tuesdayCompleted;
	
	@Column(nullable = false)
	private Boolean wednesdayCompleted;
	
	@Column(nullable = false)
	private Boolean thursdayCompleted;
	
	@Column(nullable = false)
	private Boolean fridayCompleted;
	
	@Column(nullable = false)
	private Boolean saturdayCompleted;

	public static TaskCompletion emptyProgress() {
		return new TaskCompletion(false, false, false, false, false, false);
	}

	public TaskCompletion(Boolean mondayCompleted, Boolean tuesdayCompleted, Boolean wednesdayCompleted,
			Boolean thursdayCompleted, Boolean fridayCompleted, Boolean saturdayCompleted) {
		super();
		this.mondayCompleted = mondayCompleted;
		this.tuesdayCompleted = tuesdayCompleted;
		this.wednesdayCompleted = wednesdayCompleted;
		this.thursdayCompleted = thursdayCompleted;
		this.fridayCompleted = fridayCompleted;
		this.saturdayCompleted = saturdayCompleted;
	}
}

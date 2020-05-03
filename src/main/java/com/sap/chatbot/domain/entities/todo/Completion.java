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
public class Completion {

	@Column(nullable = false, name="monday_completed")
	private Boolean monday;
	
	@Column(nullable = false, name="tuesday_completed")
	private Boolean tuesday;
	
	@Column(nullable = false, name="wednesday_completed")
	private Boolean wednesday;
	
	@Column(nullable = false, name="thursday_completed")
	private Boolean thursday;
	
	@Column(nullable = false, name="friday_completed")
	private Boolean friday;
	
	@Column(nullable = false, name="saturday_completed")
	private Boolean saturday;

	public static Completion emptyProgress() {
		return new Completion(false, false, false, false, false, false);
	}

	public Completion(Boolean mondayCompleted, Boolean tuesdayCompleted, Boolean wednesdayCompleted,
			Boolean thursdayCompleted, Boolean fridayCompleted, Boolean saturdayCompleted) {
		super();
		this.monday = mondayCompleted;
		this.tuesday = tuesdayCompleted;
		this.wednesday = wednesdayCompleted;
		this.thursday = thursdayCompleted;
		this.friday = fridayCompleted;
		this.saturday = saturdayCompleted;
	}
}

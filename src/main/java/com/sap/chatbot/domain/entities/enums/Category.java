package com.sap.chatbot.domain.entities.enums;

import java.util.Arrays;

public enum Category {

	SPORT("sport"), 
	PROGRAMMING("programming"), 
	HOUSE("house"), 
	RELAXATION("relaxation"), 
	OTHER("other");

	public static Category get(String val) {
		return Arrays.stream(Category.values()).filter(e -> e.filterValue.equals(val)).findAny()
				.orElse(Category.OTHER);
	}

	private final String filterValue;

	Category(final String filterValue) {
	      this.filterValue = filterValue;
	  }

	@Override
	public String toString() {
		return filterValue;
	}
}

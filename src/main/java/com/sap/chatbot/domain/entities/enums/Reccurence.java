package com.sap.chatbot.domain.entities.enums;

import java.util.Arrays;

public enum Reccurence {

	DAILY("daily"), 
	WEEKLY("weekly"), 
	OTHER("other");

	public static Reccurence get(String val) {
		return Arrays.stream(Reccurence.values()).filter(e -> e.filterValue.equals(val)).findAny()
				.orElse(Reccurence.OTHER);
	}

	private final String filterValue;

	Reccurence(final String filterValue) {
	      this.filterValue = filterValue;
	  }

	@Override
	public String toString() {
		return filterValue;
	}
}

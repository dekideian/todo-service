package com.sap.chatbot.forms;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoForm {

	@JsonProperty("name") 
	private String name;

	@JsonProperty("goal") 
	private String goal; // result 100 ..

	@JsonProperty("category") 
	private String category;

	@JsonProperty("reccurence") 
	private String reccurernce;

	@JsonProperty("weekNr") 
	private Short weekNr; // default - this week. o, next week 1 and so on

}

package com.allobjects;

public class AddToDoTask {
	
	String taskname;
	String description;
	String email;
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public AddToDoTask(String taskname, String description, String email) {
		super();
		this.taskname = taskname;
		this.description = description;
		this.email = email;
	}
	public AddToDoTask() {
		super();
		
	}
	
	

}

package com.allobjects;

public class ToDo {
	
	private String taskname;
	private String description;
	private String status;
	private int taskid;
	public ToDo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ToDo(String taskname, String description, String status, int Taskid) {
		super();
		this.taskname = taskname;
		this.description = description;
		this.status = status;
		this.taskid = Taskid;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTaskid() {
		return taskid;
	}
	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}
	
	

}

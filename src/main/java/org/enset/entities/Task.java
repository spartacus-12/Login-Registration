package org.enset.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Task implements Serializable{
	@Id
	@GeneratedValue
	private Long id ;
	private String taskName ;
	public Task(Long id, String taskName) {
		super();
		this.id = id;
		this.taskName = taskName;
	}
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

}

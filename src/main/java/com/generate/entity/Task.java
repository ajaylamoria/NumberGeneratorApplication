package com.generate.entity;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Configuration
public class Task {
	
	private String UUID;
	
	private String goal;
	
	private String step;
	
	private List<Integer> result;
	 
	 private String status;
	 
	 public Task(){
		 
	 }
	 
	public Task(String uUID, String goal, String step, List<Integer> result, String status) {
		super();
		UUID = uUID;
		this.goal = goal;
		this.step = step;
		this.result = result;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public List<Integer> getResult() {
		return result;
	}
	public void setResult(List<Integer> result) {
		this.result = result;
	}
	 
	 
}

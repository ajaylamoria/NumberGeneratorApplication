package com.generate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.generate.entity.Task;

public interface TaskService {

	public void generate(Task task,String uuid);
	public String getStatus(String Uuid);
	public List<List<Integer>> getNumList(String Uuid);
	public void bulkGenerate(List<Task> tasks,String uuid);
	public void generateBulk(Task task,String uuid);
	public List<Integer> generateTask(Task t); 
	public void getRealTimeStatus(Task task, boolean flag);
}

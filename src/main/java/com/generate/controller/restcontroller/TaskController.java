package com.generate.controller.restcontroller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generate.entity.Task;
import com.generate.service.TaskService;



@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	@PostMapping("/generate")
	public String generate(@RequestBody Task task) {
		String uuid = UUID.randomUUID().toString();
		taskService.generate(task, uuid);

		return uuid;
	}

	@GetMapping("/api/tasks/{UUID}/status")
	public String getStatus(@PathVariable String UUID) {
		String status = taskService.getStatus(UUID);
		return status;
	}

	@GetMapping("/api/tasks/{UUID}/getNumList")
	public List<List<Integer>> getNumList(@PathVariable String UUID) {
		List<List<Integer>> list = taskService.getNumList(UUID);
		return list;
	}

	@PostMapping("/bulkGenerate")
	public String generate(@RequestBody List<Task> tasks) {
		String uuid = UUID.randomUUID().toString();
		taskService.bulkGenerate(tasks, uuid);
		return uuid;
	}
}

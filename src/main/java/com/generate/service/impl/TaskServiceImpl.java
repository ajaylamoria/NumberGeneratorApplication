package com.generate.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.generate.entity.Task;

import com.generate.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	private Map<String, List<Task>> map = new HashMap<>();
	private Map<String, List<Task>> mapBulk = new HashMap<>();
	private boolean status = false;
	private List<Task> bulkLists = new ArrayList<>();

	@Async
	@Override
	public void generate(Task task, String uuid) {
		getRealTimeStatus(task, status);
		generateBulk(task, uuid);
		status = true;
		getRealTimeStatus(task, status);

	}

	@Override
	public void generateBulk(Task task, String uuid) {
		getRealTimeStatus(task, status);
		bulkLists.add(task);
		map.put(uuid, bulkLists);
		mapBulk.putAll(map);
		List<Integer> list = generateTask(task);
		task.setResult(list);
		status = true;
		getRealTimeStatus(task, status);

	}

	public void getRealTimeStatus(Task task, boolean flag) {
		if (flag) {
			task.setStatus("SUCCESS");
		} else {
			task.setStatus("IN-PROGRESS");
		}

	}

	@Override
	public String getStatus(String Uuid) {
		if (mapBulk.containsKey(Uuid)) {
			List<Task> tasks = mapBulk.get(Uuid);
			for (Task task : tasks) {
				return task.getStatus();
			}
		}
		return "ERROR";

	}

	@Override
	public List<List<Integer>> getNumList(String Uuid) {
		List<Task> tasks = mapBulk.get(Uuid);
		List<List<Integer>> li = new ArrayList<>();
		for (Task task : tasks) {
			li.add(task.getResult());
		}
		return li;
	}

	@Async
	@Override
	public void bulkGenerate(List<Task> tasks, String uuid) {
		this.bulkLists = new ArrayList<>();
		this.map = new HashMap<>();
		ExecutorService executor = Executors.newFixedThreadPool(tasks.size());
		for (Task task : tasks) {
			executor.submit(new MyRunnable(task, uuid));

		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}

	}

	public synchronized List<Integer> generateTask(Task t) {
		List<Integer> list = new ArrayList<>();
		Integer goal = Integer.parseInt(t.getGoal());
		Integer step = Integer.parseInt(t.getStep());
		int i = goal;
		while (i >= 0) {
			list.add(i);
			System.out.println(i);
			i = i - step;
		}
		return list;
	}

	class MyRunnable implements Callable<Object> {
		@Autowired
		private Task task;
		private String uuid;

		public MyRunnable(Task task, String uuid) {

			this.task = task;
			this.uuid = uuid;
		}

		@Override
		public Object call() throws Exception {
			generateBulk(this.task, this.uuid);
			return null;
		}

	}

}

package com.generate.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import com.generate.entity.Task;

@RunWith(SpringRunner.class)
public class TaskServiceImplTest {

	@InjectMocks
	private TaskServiceImpl taskService;

	@Test
	public void testGenerate() {
		Task obj = new Task("1", "30", "5", null, null);
		taskService.generate(obj, "1");
		assertEquals("SUCCESS", obj.getStatus());
	}

	@Test
	public void testGetRealTimeStatus() {
		Task obj = new Task("1", "30", "5", null, null);
		taskService.getRealTimeStatus(obj, true);
		assertEquals("SUCCESS", obj.getStatus());
	}

	@Test
	public void testGetNumList() {
		Task obj = new Task("1", "30", "5", Arrays.asList(30, 25, 20, 15, 10, 5), null);
		List<Task> list = new ArrayList<>();
		list.add(obj);
		Map<String, List<Task>> mapBulk = new HashMap<>();
		mapBulk.put("1", list);
		ReflectionTestUtils.setField(taskService, "mapBulk", mapBulk);
		List<List<Integer>> res = taskService.getNumList("1");
		assertNotNull(res);
	}

	@Test
	public void testGetStatus() {
		Task obj = new Task("1", "30", "5", Arrays.asList(30, 25, 20, 15, 10, 5), "SUCCESS");

		List<Task> list = new ArrayList<>();
		list.add(obj);
		Map<String, List<Task>> mapBulk = new HashMap<>();
		mapBulk.put("1", list);
		ReflectionTestUtils.setField(taskService, "mapBulk", mapBulk);
		String status = taskService.getStatus("1");
		assertEquals(status, obj.getStatus());

	}

	@Test
	public void testGetStatuseError() {
		Task obj = new Task("1", "30", "5", Arrays.asList(30, 25, 20, 15, 10, 5), "SUCCESS");
		List<Task> list = new ArrayList<>();
		list.add(obj);
		Map<String, List<Task>> mapBulk = new HashMap<>();
		mapBulk.put("1", list);
		ReflectionTestUtils.setField(taskService, "mapBulk", mapBulk);
		String status = taskService.getStatus("2");
		assertEquals("ERROR", status);

	}

}

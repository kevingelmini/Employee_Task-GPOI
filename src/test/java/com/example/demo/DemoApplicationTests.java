package com.example.demo;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domains.Employee;
import com.example.demo.domains.Task;
import com.example.demo.repositories.EmployeeRepo;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.TaskService;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	TaskService taskService;

	@Test
	void contextLoads() {

		List <Task> t = taskService.findAll();
		System.out.println(t);
		
	}

}

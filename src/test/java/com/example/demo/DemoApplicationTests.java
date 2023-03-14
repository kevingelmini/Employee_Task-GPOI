package com.example.demo;

import java.util.HashSet;
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

		//employeeRepo.deleteById(587191L);
		Optional<Employee> e = this.employeeService.findById(587191L);
		Optional<Task> t = this.taskService.findById(3142L);
		if(e.isPresent() && t.isPresent()){
			
			Employee eOggetto = e.get();
			Task tOggetto = t.get();
			Set<Task> t2 = eOggetto.getTasks();
			t2.add(tOggetto);
			employeeService.save(eOggetto);
		}else
			System.out.println("EMP-TASK non trovati");
	}

}

package com.example.demo.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domains.Employee;
import com.example.demo.domains.Project;
import com.example.demo.domains.Task;
import com.example.demo.domains.records.TaskRecord;
import com.example.demo.repositories.EmployeeRepo;

import com.example.demo.repositories.TaskRepository;

@Service
public class TaskService {

    TaskRepository taskRepository;
    EmployeeRepo employeeRepo;
    ProjectService projectService;
    

    public TaskService(TaskRepository task,EmployeeRepo employeeRepo,ProjectService projectService){
        this.taskRepository=task;
        this.employeeRepo=employeeRepo;
        this.projectService=projectService;

        
    }

    public List<Task> findAll(){
        return this.taskRepository.findAll();
    }
    public Optional<Task>findById(Long id){
        Optional<Task> opt=this.taskRepository.findById(id);
        return opt;
    } 

    public Optional<Task> save(TaskRecord entity ){
        Task t = new Task();
        Optional<Employee> opte= this.employeeRepo.findById(entity.coordinator_id());
        Optional<Project> optp= this.projectService.findById(entity.project_id());
        Project p= optp.get();
        t.setCoordinator(opte.get());
       
        t.setProject(p);
        t.setTask_end_date(entity.task_end_date());
        t.setTask_start_date(entity.task_start_date());
        t.setTask_status(entity.task_status());
        t.setTask_id(entity.task_id());
        t.setTask_name(entity.task_name());
        Task task=this.taskRepository.save(t);
        Optional<Task> opt=Optional.ofNullable(task);
        return opt;
    }
    public Optional<Task> update(Task entity ){
        Task task=this.taskRepository.save(entity);
        Optional<Task> opt=Optional.ofNullable(task);
        return opt;
    }
    public Optional<Task>delete(Long id){
        Optional<Task> opt=this.taskRepository.findById(id);
        if (opt.isPresent()){
            this.taskRepository.deleteById(id);
        }
        return opt;
    }
}



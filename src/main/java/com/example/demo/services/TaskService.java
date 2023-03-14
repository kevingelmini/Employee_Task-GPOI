package com.example.demo.services;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.demo.domains.Employee;
import com.example.demo.domains.Task;
import com.example.demo.repositories.EmployeeRepo;
import com.example.demo.repositories.TaskRepository;

@Service
public class TaskService {

    TaskRepository task;

    public TaskService(TaskRepository task){
        this.task=task;
    }

    public List<Task> findAll(){
        return this.task.findAll();
    }
    public Optional<Task>findById(Long id){
        Optional<Task> opt=this.task.findById(id);
        return opt;
    } 

    public Optional<Task> save(Task entity ){
        Task task=this.task.save(entity);
        Optional<Task> opt=Optional.ofNullable(task);
        return opt;
    }
    public Optional<Task> update(Task entity ){
        Task task=this.task.save(entity);
        Optional<Task> opt=Optional.ofNullable(task);
        return opt;
    }
    public Optional<Task>delete(Long id){
        Optional<Task> opt=this.task.findById(id);
        if (opt.isPresent()){
            this.task.deleteById(id);
        }
        return opt;
    }
}



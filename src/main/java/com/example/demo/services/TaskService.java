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
import com.example.demo.repositories.ProjectRepo;
import com.example.demo.repositories.TaskRepository;

@Service
public class TaskService {

    TaskRepository taskRepository;
    EmployeeRepo employeeRepo;
    ProjectRepo projectRepo;

    public TaskService(TaskRepository task, EmployeeRepo employeeRepo, ProjectRepo projectRepo) {
        this.taskRepository = task;
        this.employeeRepo = employeeRepo;
        this.projectRepo = projectRepo;
    }

    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    public Optional<Task> findById(Long id) {
        Optional<Task> opt = this.taskRepository.findById(id);
        return opt;
    }

    // public Optional<Task> save(TaskRecord entity ){
    // Task t = new Task();
    // Optional<Employee> opte= this.employeeRepo.findById(entity.coordinator_id());
    // Optional<Project> optp= this.projectService.findById(entity.project_id());
    // Project p= optp.get();
    // t.setCoordinator(opte.get());

    // t.setProject(p);
    // t.setTask_end_date(entity.task_end_date());
    // t.setTask_start_date(entity.task_start_date());
    // t.setTask_status(entity.task_status());
    // t.setTask_id(entity.task_id());
    // t.setTask_name(entity.task_name());
    // Task task=this.taskRepository.save(t);
    // Optional<Task> opt=Optional.ofNullable(task);
    // return opt;
    // }
    // public Optional<Task> save(Task entity ){
    public Optional<Task> save(Task entity) {
        Optional<Task> opt = Optional.ofNullable(null);
        Optional<Employee> opte = this.employeeRepo.findById(entity.getCoordinator().getEmployee_id());

        Optional<Project> optp = this.projectRepo.findById(entity.getProject().getProject_id());
        if (opte.isPresent() && optp.isPresent()) {
            Employee e = opte.get();
            entity.setCoordinator(e);
            Project p = optp.get();
            entity.setProject(p);
            Task task = this.taskRepository.save(entity);
            opt = Optional.ofNullable(task);

        }
        return opt;

        // return this.employeeRepo.
        // findById(entity.getCoordinator().getEmployee_id())
        // .map((coordinator) -> {
        // return this.projectRepo.findById(entity.getProject().getProject_id())
        // .map(project -> {
        // entity.setCoordinator(coordinator);
        // entity.setProject(project);
        // Task t= this.taskRepository.save(entity);
        // return t;
        // })
        // })

    }

    public Optional<Task> update(Task entity) {
        Task task = this.taskRepository.save(entity);
        Optional<Task> opt = Optional.ofNullable(task);
        return opt;
    }

    public Optional<Task> delete(Long id) {
        Optional<Task> opt = this.taskRepository.findById(id);
        if (opt.isPresent()) {
            this.taskRepository.deleteById(id);
        }
        return opt;
    }
}

package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domains.Employee;

import jakarta.transaction.Transactional;
//per accedere al database passiamo per repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}

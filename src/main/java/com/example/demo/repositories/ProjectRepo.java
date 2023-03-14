package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domains.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {

}

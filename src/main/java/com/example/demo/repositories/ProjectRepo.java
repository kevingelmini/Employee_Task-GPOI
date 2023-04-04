package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domains.Project;

import jakarta.transaction.Transactional;
//Nel caso in cui volessimo ade sempio solo gli Id dei cordinatori dovremmo avere una classe che esprimo che attributi vogliamo
//Nel repository dovremmo chiamare con l'annotation @Query la view, attraverso il DBMS, creato nel mio script SQL dove facciamo la join e richiamiamo i dati che ci interessano
//Nel controller poi esponiamo un api che ci permette di chiamare il metodo appena creato
public interface ProjectRepo extends JpaRepository<Project, Long> {

}

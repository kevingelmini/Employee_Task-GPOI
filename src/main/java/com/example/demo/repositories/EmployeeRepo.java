package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domains.Employee;

import jakarta.transaction.Transactional;
//per accedere al database passiamo per repository
//Estensione di un'interfaccia già esistente (Employee)
//Interface --> layer
//Si occupa della peristenza dei dati e stabilisce il protocollo
//repositories → interfaccia che permette di tipizzare, con il nome della classe, la gestione della persistenza
//L’interfaccia che estende JpaRepository<Class,typeOfChiavePrimaria> ha parametri di classe generics.
//Rappresentazione della classe specificata nella corrispondente tabella nel DB e permette la gestione della tabella tramite database
//Dà la possibilità di utilizzare metodi di HTTP
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}

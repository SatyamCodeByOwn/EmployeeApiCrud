package com.example.EmployeeApiCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EmployeeApiCrud.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
		
}

package com.example.EmployeeApiCrud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmployeeApiCrud.model.Employee;
import com.example.EmployeeApiCrud.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    
    @PostMapping("/employees")
    public String createNewEmployee(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return "Employee created in Database...";
    }
    
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
    	List<Employee> empList=new ArrayList<>();
    	employeeRepository.findAll().forEach(empList::add);
    	return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
    }
    
    @GetMapping("/employees/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long empId){
    	Optional<Employee> emp=employeeRepository.findById(empId);
    	if(emp.isPresent()) {
    		return new ResponseEntity<Employee>(emp.get(), HttpStatus.FOUND);
    	}
    	else {
    		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    	}
    }
    
    @PutMapping("/employees/{empId}")
    public String updateEmployeeById(@PathVariable long empId,@RequestBody Employee employee) {
    	Optional<Employee> emp=employeeRepository.findById(empId);
    	if(emp.isPresent()) {
    		Employee existEmp=emp.get();
    		existEmp.setEmp_age(employee.getEmp_age());
    		existEmp.setEmp_city(employee.getEmp_city());
    		existEmp.setEmp_salary(employee.getEmp_salary());
    		existEmp.setEmp_name(employee.getEmp_name());
    		
    		employeeRepository.save(existEmp);
    		return "Employee Deatils against Id "+empId+" updated";
    	}
    	else {
    		return "Employee Deatils against Id "+empId+" does not exist";
    	}
    }
    
    @DeleteMapping("/employees/{empId}")
    public String deleteEmployeeById(@PathVariable long empId) {
    	employeeRepository.deleteById(empId);
    	return "Employee deleted successfully";
    }
    
    @DeleteMapping("employees")
    public String deleteAllEmployees() {
    	employeeRepository.deleteAll();
    	return "All Employees Data Deleted";
    }
}


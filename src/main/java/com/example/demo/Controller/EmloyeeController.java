package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Employee;
import com.example.demo.Service.EmployeeService;

@RestController
@RequestMapping(value = "/employees")
public class EmloyeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
	    return employeeService.createEmployee(employee);
	}

	@GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
		return employeeService.getAllTutorials();
	}
	
	@GetMapping(params = "fullName")
	public ResponseEntity<List<Employee>> getEmployeeByFullName(@RequestParam String fullName) {
		return employeeService.getEmployeeByFullName(fullName);
	}
	
	@GetMapping(params = "empNo")
	public ResponseEntity<Employee> getEmployeeByEmpNo(@RequestParam String empNo) {
		return employeeService.getEmployeeByEmpNo(empNo);
	}
}

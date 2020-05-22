package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

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
	
	@GetMapping(params = "zipCode")
	public ResponseEntity<List<Employee>> getEmployeeByAddressZipCode(@RequestParam long zipCode) {
		return employeeService.getEmployeeByAddressZipCode(zipCode);
	}
	
	@GetMapping(params = "salaryGreaterThan")
	public ResponseEntity<List<Employee>> getEmployeesBySalaryGreaterThan(
			@RequestParam("salaryGreaterThan") Double salary) {
		return employeeService.getEmployeesBySalaryGreaterThan(salary);
	}
	
	@GetMapping(path = "/salary")
	public ResponseEntity<List<Employee>> getEmployeesBySalaryRange(@RequestParam Double maxSalary, 
			@RequestParam Double minSalary) {
		return employeeService.getEmployeesBySalaryRange(maxSalary, minSalary);
	}
	
	@GetMapping(params = "regex")
	public ResponseEntity<List<Employee>> getEmployeesByRegex(@RequestParam String regex) {
		return employeeService.getEmployeesByRegex(regex);
	}
	
	@GetMapping( params = {"fullName", "band"} )
	public ResponseEntity<List<Employee>> getEmployeesByFullNameAndBand(@RequestParam String fullName, 
			@RequestParam String band) {
		return employeeService.getEmployeesByFullNameAndBand(fullName, band);
	}
	
	@GetMapping("/page")
    public ResponseEntity<Map<String, Object>> getAllEmployeesInPage(
    		@RequestParam(name = "pageNo", defaultValue = "0") int pageNo, 
    		@RequestParam(name = "pageSize", defaultValue = "2") int pageSize, 
    		@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
		return employeeService.getAllEmployeesInPage(pageNo, pageSize, sortBy);
	}
	
	@PostMapping("/example")
    public ResponseEntity<List<Employee>> getAllByExample(@RequestBody Employee employee) {
		return employeeService.getAllByExample(employee);
	}
}

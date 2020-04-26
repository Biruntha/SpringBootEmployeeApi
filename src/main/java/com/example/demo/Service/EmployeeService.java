package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.EmployeeDao;
import com.example.demo.DAO.EmployeeRepositoryCustom;
import com.example.demo.Model.Employee;

@Service
public class EmployeeService {
	@Autowired
    private EmployeeRepositoryCustom employeeRepositoryCustom;
 
    @Autowired
    private EmployeeDao employeeDao;

	public ResponseEntity<List<Employee>> getAllTutorials() {
		try {
		    List<Employee> employees = new ArrayList<Employee>();
		
		    employeeDao.findAll().forEach(employees::add);
		
		    if (employees.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		
		    return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Employee>> getEmployeeByFullName(String fullName) {
		try {
		    List<Employee> employees = employeeDao.findByFullNameLike(fullName);

		    if (employees.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		    return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	public ResponseEntity<Employee> getEmployeeByEmpNo(String empNo) {
		Optional<Employee> employee = employeeDao.findByEmpNo(empNo);
		if (employee.isPresent()) {
			return new ResponseEntity<>(employee.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
//		try {
//			Optional<Employee> employee = employeeDao.findByEmpNo(empNo);
//			return new ResponseEntity<>(employee.get(), HttpStatus.OK);
//		} catch (Exception e) {
//		    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//		}
	}

	public ResponseEntity<Employee> createEmployee(Employee employee) {
		long id = employeeRepositoryCustom.getMaxEmpId() + 1;
		try {
			Employee employeeNew = employeeDao.save(new Employee(id, employee.getEmpNo(), employee.getFullName(), new Date()));
		    return new ResponseEntity<>(employeeNew, HttpStatus.CREATED);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
}

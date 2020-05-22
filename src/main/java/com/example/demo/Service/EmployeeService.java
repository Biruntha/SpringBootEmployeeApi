package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	}

	public ResponseEntity<List<Employee>> getEmployeeByAddressZipCode(long zipCode) {
		try {
		    List<Employee> employees = employeeDao.findByAddressZipCode(zipCode);

		    if (employees.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		    return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	public ResponseEntity<Employee> createEmployee(Employee employee) {
		long id = employeeRepositoryCustom.getMaxEmpId() + 1;
		try {
			Employee employeeNew = employeeDao.save(new Employee(id, employee.getEmpNo(), employee.getFullName(),
					employee.getBand(), employee.getSalary(), new Date(), employee.getAddress()));
		    return new ResponseEntity<>(employeeNew, HttpStatus.CREATED);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	public ResponseEntity<Map<String, Object>> getAllEmployeesInPage(int pageNo, int pageSize, String sortBy) {
		try {
			Map<String, Object> response = new HashMap<>();
		    Sort sort = Sort.by(sortBy);
			Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		    Page<Employee> page = employeeDao.findAll(pageable);
		    response.put("data", page.getContent());
		    response.put("Total no of pages", page.getTotalPages());
		    response.put("Total no of elements", page.getTotalElements());
		    response.put("Current page no", page.getNumber());
		    
		    return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Employee>> getAllByExample(Employee employee) {
		try {
		    List<Employee> employees = new ArrayList<Employee>();
		    
		    ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreCase().withMatcher("fullName", GenericPropertyMatcher.of(StringMatcher.ENDING));
		    Example<Employee> example = Example.of(employee, matcher);
		    employeeDao.findAll(example).forEach(employees::add);
		
		    if (employees.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		
		    return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Employee>> getEmployeesBySalaryGreaterThan(Double salary) {
		try {
		    List<Employee> employees = employeeDao.findBySalaryGreaterThan(salary);
		
		    if (employees.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		
		    return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Employee>> getEmployeesBySalaryRange(Double maxSalary, Double minSalary) {
		try {
		    List<Employee> employees = employeeDao.findEmployeesBySalaryRange(maxSalary, minSalary);
		
		    if (employees.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		
		    return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Employee>> getEmployeesByRegex(String regex) {
		try {
		    List<Employee> employees = employeeDao.findEmployeesByRegex(regex);
		
		    if (employees.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		
		    return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Employee>> getEmployeesByFullNameAndBand(String fullName, String band) {
		try {
		    List<Employee> employees = employeeDao.findEmployeesByFullNameAndBand(fullName, band);
		
		    if (employees.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		
		    return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

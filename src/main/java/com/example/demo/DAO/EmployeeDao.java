package com.example.demo.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.Model.Employee;

public interface EmployeeDao extends MongoRepository<Employee, Long> {
	Optional<Employee> findByEmpNo(String empNo);
	 
    List<Employee> findByFullNameLike(String fullName);
 
    List<Employee> findBySalaryGreaterThan(Double salary);
    
    List<Employee> findByAddressZipCode(long zipCode);
 
    // Supports native JSON query string
    @Query("{'fullName' : ?0 , 'band' : ?1}") 
    List<Employee> findEmployeesByFullNameAndBand(String fullName, String band);
    
    @Query("{salary : {$lt : ?0, $gt : ?1}}")
    List<Employee> findEmployeesBySalaryRange(Double maxSalary, Double minSalary);

    @Query("{ 'fullName' : { $regex: ?0 } }") 
    List<Employee> findEmployeesByRegex(String regexp);
}

package com.example.demo.DAO;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Address;
import com.example.demo.Model.Employee;
import com.mongodb.client.result.UpdateResult;

@Repository
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {
	
	@Autowired
    MongoTemplate mongoTemplate;
 
    public long getMaxEmpId() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "id"));
        query.limit(1);
        Employee maxObject = mongoTemplate.findOne(query, Employee.class);
        if (maxObject == null) {
            return 0L;
        }
        return maxObject.getId();
    }
 
    @Override
    public long updateEmployee(String empNo, String fullName, String band, Double salary, 
    		Date hireDate, Address address) {
        Query query = new Query(Criteria.where("empNo").is(empNo));
        Update update = new Update();
        update.set("fullName", fullName);
        update.set("band", band);
        update.set("salary", salary);
        update.set("hireDate", hireDate);
        update.set("address", address);
 
        UpdateResult result = this.mongoTemplate.updateFirst(query, update, Employee.class);
 
        if (result != null) {
            return result.getModifiedCount();
        }
        return 0;
    }
}

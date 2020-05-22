package com.example.demo.DAO;

import java.util.Date;

import com.example.demo.Model.Address;

public interface EmployeeRepositoryCustom {
	public long getMaxEmpId();
    
    public long updateEmployee(String empNo, String fullName, String band, Double salary, 
    		Date hireDate, Address address);
}

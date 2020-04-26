package com.example.demo.DAO;

import java.util.Date;

public interface EmployeeRepositoryCustom {
	public long getMaxEmpId();
    
    public long updateEmployee(String empNo, String fullName, Date hireDate);
}

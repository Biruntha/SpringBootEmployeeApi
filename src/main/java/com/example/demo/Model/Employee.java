package com.example.demo.Model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Employee")
public class Employee {
 
    @Id
    private Long id;
 
    @Field(value = "Emp_No")
    private String empNo;
 
    @Field(value = "Full_Name")
    private String fullName;
    
    @Field(value = "Job_band")
    private String band;
    
    @Field(value = "Salary")
    private Double salary;
    
    @Field(value = "Hire_Date")
    private Date hireDate;
    private Address address;
    
	public Employee(Long id, String empNo, String fullName, String band, Double salary, Date hireDate, Address address) {
		super();
		this.id = id;
		this.empNo = empNo;
		this.fullName = fullName;
		this.band = band;
		this.salary = salary;
		this.hireDate = hireDate;
		this.address = address;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getBand() {
		return band;
	}
	public void setBand(String band) {
		this.band = band;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", empNo=" + empNo + ", fullName=" + fullName + ", band=" + band + ", salary="
				+ salary + ", hireDate=" + hireDate + ", address=" + address + "]";
	}
}

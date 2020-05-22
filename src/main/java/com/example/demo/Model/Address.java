package com.example.demo.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Address {
	String city;
    String state;
    long zipCode;
    
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getZipCode() {
		return zipCode;
	}
	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}
}

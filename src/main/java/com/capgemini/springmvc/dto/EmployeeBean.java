package com.capgemini.springmvc.dto;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class EmployeeBean implements Serializable {
	
	int empId;
	String empName;
	int age;
	double salary;
	String designation;
	String password;

}

package com.capgemini.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.springmvc.dao.EmployeeDAO;
import com.capgemini.springmvc.dto.EmployeeBean;

public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	EmployeeDAO empdao;
	
	@Override
	public EmployeeBean getEmployeeByid(int Id) {
		return empdao.getEmployeeByid(Id);
	}

	@Override
	public boolean addEmployee(EmployeeBean bean) {
		return empdao.addEmployee(bean);
	}

	@Override
	public boolean updateEmployee(EmployeeBean bean) {
		return empdao.updateEmployee(bean);
	}

	@Override
	public boolean deleteEmployee(int Id) {
		return empdao.deleteEmployee(Id);
	}

	@Override
	public List<EmployeeBean> getAllEmployees() {
		return empdao.getAllEmployees();
	}

	@Override
	public EmployeeBean authenticate(int empId, String password) {
		return empdao.authenticate(empId, password);
	}
	

}

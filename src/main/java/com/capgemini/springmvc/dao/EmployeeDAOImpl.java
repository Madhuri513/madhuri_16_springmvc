package com.capgemini.springmvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.capgemini.springmvc.dto.EmployeeBean;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	Connection connection = null;
	EmployeeBean employeeInfoBean = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public EmployeeBean getEmployeeByid(int empId) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_db", "root", "root");
			pstmt = connection.prepareStatement("select * from employee_info where empId=?");
			pstmt.setInt(1, empId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				employeeInfoBean = new EmployeeBean();
				employeeInfoBean.setEmpId(rs.getInt("empId"));
				employeeInfoBean.setAge(rs.getInt("age"));
				employeeInfoBean.setEmpName(rs.getString("empName"));
				employeeInfoBean.setDesignation(rs.getString("designation"));
				employeeInfoBean.setPassword(rs.getString("password"));
				employeeInfoBean.setSalary(rs.getDouble("salary"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		return employeeInfoBean;
	}

	public boolean addEmployee(EmployeeBean bean) {

		String query1 = "insert into employee_info values(?,?,?,?,?,?)";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_db", "root", "root");

			pstmt = connection.prepareStatement(query1);

			pstmt.setInt(1, bean.getEmpId());
			pstmt.setString(2, bean.getEmpName());
			pstmt.setInt(3, bean.getAge());
			pstmt.setDouble(4, bean.getSalary());
			pstmt.setString(5, bean.getDesignation());
			pstmt.setString(6, bean.getPassword());

			int result = pstmt.executeUpdate();

			if (result != 0) {
				System.out.println("values are inserted succefully");
				return true;
			}

			connection.close();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return false;

	}

	public boolean updateEmployee(EmployeeBean bean) {
		boolean isUpdated = false;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_db", "root", "root");
			pstmt = connection.prepareStatement("update employee_info set empname=? where empid=? ");
			pstmt.setString(1, bean.getEmpName());
			pstmt.setInt(2, bean.getEmpId());

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("values are updated succefully");
				isUpdated = true;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return isUpdated;
	}

	public boolean deleteEmployee(int empId) {

		boolean isDeleted = false;

		try {

			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_db", "root", "root");
			pstmt = connection.prepareStatement("delete from  employee_info  where empId=?");

			pstmt.setInt(1, empId);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("values deleted succefully");
				isDeleted = true;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return isDeleted;
	}

	public List<EmployeeBean> getAllEmployees() {
		List<EmployeeBean> listInfo = new ArrayList<EmployeeBean>();

		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp_db", "root", "root");
			pstmt = connection.prepareStatement("select * from employee_info");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				employeeInfoBean = new EmployeeBean();

				employeeInfoBean.setEmpName(rs.getString("empName"));
				employeeInfoBean.setAge(rs.getInt("age"));
				employeeInfoBean.setPassword(rs.getString("password"));
				employeeInfoBean.setDesignation(rs.getString("designation"));
				employeeInfoBean.setSalary(rs.getDouble("salary"));
				employeeInfoBean.setEmpId(rs.getInt("empId"));

				listInfo.add(employeeInfoBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		return listInfo;

	}

	public EmployeeBean authenticate(int empId, String password) {
		employeeInfoBean = getEmployeeByid(empId);
		if (!(employeeInfoBean != null && employeeInfoBean.getPassword().equals(password))) {
			employeeInfoBean = null;
		}
		return employeeInfoBean;
	}

}

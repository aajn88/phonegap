package com.payulatam.example.androidvsphonegap.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.annotation.SuppressLint;

import com.payulatam.example.androidvsphonegap.R;
import com.payulatam.example.androidvsphonegap.model.Employee;

@SuppressLint({ "UseSparseArrays", "DefaultLocale" })
public class EmployeeDAO {
	private static final Map<String, Employee> employees = new HashMap<String, Employee>();

	static {
		int id = 1;
		Employee employee = new Employee(id++ + "", "Antonio", "Jimenez", "aajn88@gmail.com", R.drawable.antonio);
		employees.put(employee.getId(), employee);
		employee = new Employee(id++ + "", "Pilly", "Bernal", "li-li-2005@hotmail.com", R.drawable.pilly);
		employees.put(employee.getId(), employee);
		employee = new Employee(id++ + "", "Antonio", "Papa", "antjim@gmail.com", R.drawable.papa);
		employees.put(employee.getId(), employee);
		employee = new Employee(id++ + "", "Xiomara", "Navas", "xiomaranavas@gmail.com", R.drawable.mama);
		employees.put(employee.getId(), employee);
	}

	public Employee create(Employee employee) {
		String employeeId = employee.getId();
		employees.put(employeeId, employee);
		return employee;
	}

	public Employee findById(String employeeId) {
		return employees.get(employeeId);
	}

	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();

		for (Entry<String, Employee> entry : EmployeeDAO.employees.entrySet()) {
			employees.add(entry.getValue());
		}

		return employees;
	}

	public List<Employee> searchEmployee(String text) {
		List<Employee> employees = new ArrayList<Employee>();

		for (Entry<String, Employee> entry : EmployeeDAO.employees.entrySet()) {
			if (isMatch(text, entry.getValue())) {
				employees.add(entry.getValue());
			}
		}

		return employees;
	}

	@SuppressLint("DefaultLocale")
	private boolean isMatch(String text, Employee e2) {
		boolean match = false;

		if (text.trim().equals("")) {
			match = true;
		} else {
			text = text.toUpperCase();
			match = e2.getId().toUpperCase().contains(text) || e2.getFirstName().toUpperCase().contains(text) || e2.getLastName().toUpperCase().contains(text)
					|| e2.getEmail().toUpperCase().contains(text);
		}

		return match;
	}
}

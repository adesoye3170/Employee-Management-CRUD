package com.example.APISpring.service;

import com.example.APISpring.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployee();

    Employee getEmployeeid(long id);

    Employee updateEmployee(Employee employee, long id);

    void deleeteEmployee(long id);
}

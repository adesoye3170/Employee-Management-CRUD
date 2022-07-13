package com.example.APISpring.service.impl;

import com.example.APISpring.exception.ResourceNotFoundException;
import com.example.APISpring.model.Employee;
import com.example.APISpring.repo.EmployeeRepository;
import com.example.APISpring.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeid(long id) {

        /**
         * This is a valid method but lamda implementation is preferred
         */

        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()){
            return employee.get();
        }else {
            throw new ResourceNotFoundException("employee", "Id", id);
        }

        /**
         * This is the lamda alternative
          *//*

        return employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", id));
*/
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        /**
         * to update employee get a new request from the client
         * check if the employee exist by checkoing by Id
         * set the new value inside the existing employee id called
         * save the employee inside the save jpa method
         */
        //step1
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Employee", "Id", id));
        //step 2
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());

        //step 3

        return employeeRepository.save(existingEmployee);


    }

    @Override
    public void deleeteEmployee(long id) {
        /**
         * check if employee with Id exist
         * if yes delete employee
         */
       employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Employee", "id", id));
        employeeRepository.deleteById(id);
    }
}

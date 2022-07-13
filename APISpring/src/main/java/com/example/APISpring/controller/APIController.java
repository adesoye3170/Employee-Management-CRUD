package com.example.APISpring.controller;

import com.example.APISpring.model.Employee;
import com.example.APISpring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class APIController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/save-employee")
    public ResponseEntity<Employee> getEmployee(@RequestBody Employee employee){
        Employee save =  employeeService.createEmployee(employee);

        return new  ResponseEntity<Employee>(save, HttpStatus.CREATED);
    }

    @GetMapping("/get-emloyee")
    public ResponseEntity<List<Employee>>  getEmployee(){
       List <Employee> getEmployee = employeeService.getAllEmployee();

       return ResponseEntity.ok().body(getEmployee);
    }

    @GetMapping("/get-id-employee/{id}")
    public ResponseEntity<Employee> getByIdEmployee(@PathVariable long id){
        Employee value = employeeService.getEmployeeid(id);
        return ResponseEntity.ok(value);
    }

    @PutMapping("/update-employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable long id){
        Employee em = employeeService.updateEmployee(employee, id);
        return ResponseEntity.ok(em);

    }

    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id){
        employeeService.deleeteEmployee(id);

        return ResponseEntity.ok("Employee has been deleted from database");
    }
}

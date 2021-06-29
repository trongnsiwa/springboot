package com.example.springboot.service;

import java.util.List;
import com.example.springboot.entity.Employee;

public interface EmployeeService {

  List<Employee> getAllEmployees();

  Employee getEmployeeById(Long id);

  Employee addEmployee(Employee emp);

  Employee updateEmployee(Employee emp, Long id);

  Employee updateEmployeeSalary(Float salary, Long id);

  boolean deleteEmployee(Long id);


}

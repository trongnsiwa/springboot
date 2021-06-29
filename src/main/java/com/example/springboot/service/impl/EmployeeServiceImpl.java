package com.example.springboot.service.impl;

import java.util.List;
import com.example.springboot.entity.Employee;
import com.example.springboot.exception.EmployeeException;
import com.example.springboot.repository.EmployeeRepository;
import com.example.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  private static final String EMP_NOT_FOUND_MSG = "Employee is not found";

  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  @Override
  public Employee getEmployeeById(Long id) {
    return employeeRepository.findById(id)
        .orElseThrow(() -> new EmployeeException(EMP_NOT_FOUND_MSG));
  }

  @Override
  public Employee addEmployee(Employee emp) {
    return employeeRepository.save(emp);
  }

  @Override
  public Employee updateEmployeeSalary(Float newSalary, Long id) {
    Employee employee = employeeRepository.findById(id)
        .orElseThrow(() -> new EmployeeException(EMP_NOT_FOUND_MSG));

    if (newSalary == null || newSalary <= 0) {
      throw new EmployeeException("Employee's salary must be higher than 0.");
    }

    employee.setSalary(newSalary);
    return employeeRepository.save(employee);
  }

  @Override
  public Employee updateEmployee(Employee emp, Long id) {
    Employee employee = employeeRepository.findById(id).orElseThrow(
        () -> new EmployeeException(EMP_NOT_FOUND_MSG));

    employee.setName(emp.getName());
    employee.setSalary(emp.getSalary());
    employee.setRole(emp.getRole());
    return employeeRepository.save(employee);
  }

  @Override
  public boolean deleteEmployee(Long id) {
    if (employeeRepository.existsById(id)) {
      employeeRepository.deleteById(id);
      return true;
    }

    return false;
  }

}

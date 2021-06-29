package com.example.springboot.restcontroller;

import java.util.List;
import javax.validation.Valid;
import com.example.springboot.entity.Employee;
import com.example.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

  @Autowired
  EmployeeService employeeService;

  @GetMapping("/all")
  public List<Employee> getAlLEmployees() {
    return employeeService.getAllEmployees();
  }

  @GetMapping("/{id}")
  public Employee getEmployeeById(@PathVariable("id") Long id) {
    return employeeService.getEmployeeById(id);
  }

  @PostMapping("/add")
  public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee emp) {
    return ResponseEntity.ok().body(employeeService.addEmployee(emp));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee emp,
      @PathVariable("id") Long id) {
    return ResponseEntity.ok().body(employeeService.updateEmployee(emp, id));
  }

  @PatchMapping("/updateSalary/{id}")
  public ResponseEntity<Employee> updateEmployeeSalary(@RequestParam Float salary,
      @PathVariable("id") Long id) {
    return ResponseEntity.ok().body(employeeService.updateEmployeeSalary(salary, id));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
    if (employeeService.deleteEmployee(id)) {
      return ResponseEntity.ok().body("Delete employee successful.");
    }

    return ResponseEntity.badRequest().body("");
  }

}

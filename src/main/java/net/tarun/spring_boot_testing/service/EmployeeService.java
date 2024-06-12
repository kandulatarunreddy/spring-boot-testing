package net.tarun.spring_boot_testing.service;

import java.util.List;

import net.tarun.spring_boot_testing.model.Employee;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
}

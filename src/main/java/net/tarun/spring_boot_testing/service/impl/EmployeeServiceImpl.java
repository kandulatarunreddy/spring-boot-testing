package net.tarun.spring_boot_testing.service.impl;

import net.tarun.spring_boot_testing.exception.ResourceNotFoundException;
import net.tarun.spring_boot_testing.model.Employee;
import net.tarun.spring_boot_testing.repository.EmployeeRepository;
import net.tarun.spring_boot_testing.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository=employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        Optional<Employee> savedEmployee=employeeRepository.findByEmail(employee.getEmail());
        if(savedEmployee.isPresent()){
            throw new ResourceNotFoundException("employee already exist with given email:"+employee.getEmail());
        }

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        // TODO Auto-generated method stub
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        // TODO Auto-generated method stub
        return employeeRepository.findById(id);
    }
}

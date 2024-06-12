package net.tarun.spring_boot_testing.service;

import net.tarun.spring_boot_testing.exception.ResourceNotFoundException;
import net.tarun.spring_boot_testing.model.Employee;
import net.tarun.spring_boot_testing.repository.EmployeeRepository;
import net.tarun.spring_boot_testing.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup(){
        //employeeRepository= Mockito.mock(EmployeeRepository.class);
        //employeeService=new EmployeeServiceImpl(employeeRepository);
        employee=Employee.builder()
        .id(1L)
        .firstName("tarun")
        .lastName("kandula")
        .email("tarun@gmail.com")
        .build();
   }

    //JUnit test for saveEmployee method
    @DisplayName("JUnit test for saveEmployee method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeOject() {
        //given - precondition or setup
       
        //System.out.println(employeeRepository);
        given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());
        given(employeeRepository.save(employee)).willReturn(employee);

        //when - action or behaviour that we are going to test
        Employee savedEmployee = employeeService.saveEmployee(employee);

        //then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();

    }

    // JUnit test for saveEmployee method
    @DisplayName("JUnit test for saveEmployee method which throws exception")
    @Test
    public void givenExistingEmail_whenSaveEmployee_thenThrowsException(){
        // given - precondition or setup
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));

        System.out.println(employeeRepository);
        System.out.println(employeeService);

        // when -  action or the behaviour that we are going test
        assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.saveEmployee(employee);
        });

        // then
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    // JUnit test for getAllEmployees method
    @DisplayName("JUnit test for getAllEmployees method")
    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList(){
        // given - precondition or setup

        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("arun")
                .lastName("reddy")
                .email("reddy@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));

        // when -  action or the behaviour that we are going test
        List<Employee> employeeList = employeeService.getAllEmployees();

        // then - verify the output
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList.size()).isEqualTo(2);
    }

    // JUnit test for getAllEmployees method
    @DisplayName("JUnit test for getAllEmployees method (negative scenario)")
    @Test
    public void givenEmptyEmployeesList_whenGetAllEmployees_thenReturnEmptyEmployeesList(){
        // given - precondition or setup

        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("arun")
                .lastName("reddy")
                .email("reddy@gmail.com")
                .build();

        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<Employee> employeeList = employeeService.getAllEmployees();

        // then - verify the output
        Assertions.assertThat(employeeList).isEmpty();
        Assertions.assertThat(employeeList.size()).isEqualTo(0);
    }


}

package net.tarun.spring_boot_testing.service;

import net.tarun.spring_boot_testing.model.Employee;
import net.tarun.spring_boot_testing.repository.EmployeeRepository;
import net.tarun.spring_boot_testing.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Optional;

public class EmployeeServiceTests {

    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    public void setup(){
        employeeRepository= Mockito.mock(EmployeeRepository.class);
        employeeService=new EmployeeServiceImpl(employeeRepository);
   }

    //JUnit test for saveEmployee method
    @DisplayName("JUnit test for saveEmployee method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeOject() {
        //given - precondition or setup
        Employee employee=Employee.builder()
                .id(1L)
                .firstName("tarun")
                .lastName("kandula")
                .email("tarun@gmail.com")
                .build();
        //System.out.println(employeeRepository);
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());
        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

        //when - action or behaviour that we are going to test
        Employee savedEmployee = employeeService.saveEmployee(employee);

        //then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();

    }

}

package com.example.springthymeleafecrude.service;

import com.example.springthymeleafecrude.model.Employee;
import com.example.springthymeleafecrude.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public void saveEmployee(Employee employee){


        employeeRepository.save(employee);
    }


    public List<Employee> getAllEmployees(){


        return (List<Employee>) employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id){
        Optional<Employee> optional=employeeRepository.findById(id);
        Employee employee = null;
        if(optional.isPresent()){
            employee=optional.get();
        }else {
            throw new RuntimeException("Employee not available" + id);
        }
        return employee;
    }
    public void deleteEmployeeById(long id){
        this.employeeRepository.deleteById(id);
    }
}

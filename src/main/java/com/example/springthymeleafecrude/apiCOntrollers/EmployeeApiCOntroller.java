/**
 * Created by mupezeni for spring-thymeleafe-crude
 * User: mupezeni
 * Date: 22/9/2022
 * Time: 19:11
 */


package com.example.springthymeleafecrude.apiCOntrollers;

import com.example.springthymeleafecrude.model.Employee;
import com.example.springthymeleafecrude.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api")
public class EmployeeApiCOntroller {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/all")
   public List<Employee> Allemployess(){
       return employeeService.getAllEmployees();
    }

    @PostMapping ("/add")
    public void addEmployee( @RequestBody Employee employee){
      employeeService.saveEmployee(employee);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmp(@PathVariable Long id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        try {
            employeeService.update(employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteEmpl/{id}")
    private void deleteEmployee(@PathVariable Long id){
            employeeService.deleteEmployeeById(id);
    }

}

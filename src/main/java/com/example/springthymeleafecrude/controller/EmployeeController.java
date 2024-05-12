package com.example.springthymeleafecrude.controller;

import com.example.springthymeleafecrude.model.Employee;
import com.example.springthymeleafecrude.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {
    @Autowired
    public EmployeeService employeeService;
    //display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("listEmp", allEmployees);
        //System.out.println("employees" + allEmployees.size());
        return "index";
    }
    @GetMapping("/ShowNewEmployeeForm")
    public String ShowNewEmployeeForm(Model model ){
        Employee employee= new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";
    }
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") @Valid Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    //Taking data from database then put it on update form
    @GetMapping("/showFormUpdate/{id}")
    public String showFormUpdate(@Valid @PathVariable(value = "id") long id,Model model){
        //get employee from the service
        Employee employee=employeeService.getEmployeeById(id);
        model.addAttribute("employee",employee);
        return"update_employee";

    }
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id){
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

    @GetMapping("/getEmployees-with-long-names")
    @ResponseBody
    Iterable<Employee> getAllEmployees(Model model) {
        Iterable<Employee> allEmployees  =  employeeService.getAllEmployees()
                .parallelStream()
                .filter(employee -> employee.getFirstName().length() > 5)
                .collect(Collectors.toList());

         model.addAttribute("listEmp", allEmployees);
         return allEmployees;
    }

}
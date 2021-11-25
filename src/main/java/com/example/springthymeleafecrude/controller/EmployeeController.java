package com.example.springthymeleafecrude.controller;

import com.example.springthymeleafecrude.model.Employee;
import com.example.springthymeleafecrude.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
            public String ShowNewEmployeeForm(Model model){
        Employee employee= new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";
    }
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }
    @GetMapping("/showFormUpdate/{id}")
    public String showFormUpdate(@PathVariable(value = "id") long id,Model model){
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
}
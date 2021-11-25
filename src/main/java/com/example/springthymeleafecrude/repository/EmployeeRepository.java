package com.example.springthymeleafecrude.repository;

import com.example.springthymeleafecrude.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long>{
}

package com.credmarg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credmarg.model.Employees;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {

}

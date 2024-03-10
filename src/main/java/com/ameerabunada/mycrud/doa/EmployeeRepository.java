package com.ameerabunada.mycrud.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ameerabunada.mycrud.models.Employee;

/**
 * EmployeeRepository
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  public List<Employee> findAllByOrderByIdDesc();
}
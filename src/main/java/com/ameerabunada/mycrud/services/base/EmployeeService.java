package com.ameerabunada.mycrud.services.base;

import java.util.List;

import com.ameerabunada.mycrud.models.Employee;

public interface EmployeeService {

  List<Employee> findAll();

  Employee findById(int id);

  Employee save(Employee employee);

  void deleteById(int id);
}

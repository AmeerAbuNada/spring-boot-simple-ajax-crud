package com.ameerabunada.mycrud.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ameerabunada.mycrud.doa.EmployeeRepository;
import com.ameerabunada.mycrud.models.Employee;
import com.ameerabunada.mycrud.services.base.EmployeeService;

@Service
public class EmployeeSeriveImpl implements EmployeeService {

  private EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeSeriveImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public List<Employee> findAll() {
    return employeeRepository.findAllByOrderByIdDesc();
  }

  @Override
  public Employee findById(int id) {
    Optional<Employee> optionalEmployee = employeeRepository.findById(id);

    if (optionalEmployee.isPresent()) {
      return optionalEmployee.get();
    }

    return null;
  }

  @Override
  public Employee save(Employee employee) {
    return employeeRepository.save(employee);
  }

  @Override
  public void deleteById(int id) {
    employeeRepository.deleteById(id);
  }

}

package com.ameerabunada.mycrud.controllers.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ameerabunada.mycrud.models.Employee;
import com.ameerabunada.mycrud.services.base.EmployeeService;

@Controller
@RequestMapping({"/employees", "/employees/"})
public class EmployeeController {

  EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping({ "/", "" })
  public String index(Model model) {

    List<Employee> employees = employeeService.findAll();
    
    model.addAttribute("employee", new Employee());
    model.addAttribute("employees", employees);

    return "employees/index";
  }

}

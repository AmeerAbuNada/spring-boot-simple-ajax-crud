package com.ameerabunada.mycrud.controllers.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ameerabunada.mycrud.helpers.Response;
import com.ameerabunada.mycrud.models.Employee;
import com.ameerabunada.mycrud.services.base.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping({ "/employees", "/employees/" })
public class EmployeeRestController {

  EmployeeService employeeService;

  @Autowired
  public EmployeeRestController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Ensure this matches your @DateTimeFormat
    dateFormat.setLenient(false);
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
  }

  @PostMapping({ "/", "" })
  public ResponseEntity<?> store(@Valid Employee employee, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      List<String> errors = new ArrayList<>();
      for (ObjectError error : bindingResult.getAllErrors()) {
        errors.add(error.getDefaultMessage());
      }
      return Response.error(400, errors.get(0), errors);
    }
    Employee savedEmployee = employeeService.save(employee);
    return Response.success(201, "Employee Created Successfully!", savedEmployee);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> show(@PathVariable String id) {
    try {
      int parsedInt = Integer.parseInt(id);
      Employee employee = employeeService.findById(parsedInt);
      if (employee != null) {
        return Response.success(200, "Employee Found!", employee);
      } else {
        return Response.error(404, "Employee Not Found.", employee);
      }
    } catch (Exception ex) {
      return Response.error(404, "Employee Not Found.", null);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody Employee employee, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      List<String> errors = new ArrayList<>();
      for (ObjectError error : bindingResult.getAllErrors()) {
        errors.add(error.getDefaultMessage());
      }
      return Response.error(400, errors.get(0), errors);
    }
    try {
      int parsedInt = Integer.parseInt(id);
      employee.setId((long) parsedInt);
      Employee employeeFromDb = employeeService.findById(parsedInt);
      if(employeeFromDb != null) {
        employee.setCreatedAt(employeeFromDb.getCreatedAt());
        Employee savedEmployee = employeeService.save(employee);
        return Response.success(200, "Employee Updated Successfully!", savedEmployee);
      }
      return Response.error(404, "Employee Not Found.", null);
    } catch (Exception ex) {
      return Response.error(404, "Employee Not Found.", null);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
    try {
      int convertedInt = Integer.parseInt(id);
      employeeService.deleteById(convertedInt);
      return Response.success(200, "Employee Deleted Successfully!", null);
    } catch (Exception ex) {
      return Response.error(400, "Invalid Id Entered", null);
    }
  }

}

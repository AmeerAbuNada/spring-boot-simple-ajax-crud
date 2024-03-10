package com.ameerabunada.mycrud.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {
  
  @GetMapping({"/", ""})
  public String redirectTo() {
      return "redirect:/employees";
  }
  
}

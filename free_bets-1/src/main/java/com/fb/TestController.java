package com.fb; 

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class TestController {
 @RequestMapping("/api/hello")

 public String greet() {
  System.out.println("invoce testes");
  return "Hello from the other side!!!";
 }
}
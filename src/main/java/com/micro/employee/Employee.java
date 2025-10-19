package com.micro.employee;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "employee")
public class Employee {
  
    @Id
    private String id;
    private Long departmentId;
    private String name;
    private int age;
    private String position;
}

package com.micro.employee;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {
    
    private EmployeeService employeeService;

    @GetMapping(path = "list")
    public ResponseEntity<List<Employee>> getList(){
        var employees = employeeService.getListEmployee();
        return ResponseEntity.ok(employees);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "create")
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        var newEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(newEmployee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "update/{id}")
    public ResponseEntity<Employee> update(@PathVariable String id, @RequestBody Employee employee){
        var updatedEmployee = employeeService.updateEmployee(employee, id);
        if(updatedEmployee.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(updatedEmployee.get());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable String id){
        var employee = employeeService.getEmployee(id);
        if(employee.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(employee.get());
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Employee>> getEmployeesPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Employee> employees = employeeService.getEmployeesPageable(page, size);
        return ResponseEntity.ok(employees);
    }
}

package com.micro.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {
    
    private EmployeeRepo employeeRepo;

    public Employee saveEmployee(Employee employee){
        var newEmployee = employeeRepo.save(employee);
        return newEmployee;
    }

    public List<Employee> getListEmployee(){
        return employeeRepo.findAll();
    }

    public Optional<Employee> updateEmployee(Employee employee, String id){

        Optional<Employee> getEmployee = employeeRepo.findById(id);

        if(getEmployee.isEmpty()){
            return Optional.empty();
        }
        employee.setId(id);
        var updateEmployee = employeeRepo.save(employee);
        return Optional.of(updateEmployee);
    }
    
    public Optional<Employee> getEmployee(String id){
        return employeeRepo.findById(id);
    } 

    public Page<Employee> getEmployeesPageable(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return employeeRepo.findAll(pageable);
    }
}

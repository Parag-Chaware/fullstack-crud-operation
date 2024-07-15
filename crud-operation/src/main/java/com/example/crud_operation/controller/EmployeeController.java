package com.example.crud_operation.controller;

import com.example.crud_operation.exception.ResourceNotFountException;
import com.example.crud_operation.model.Employee;
import com.example.crud_operation.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long employeeId) throws ResourceNotFountException {
    Employee employee = employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFountException("Employee not found for the id: " + employeeId));
    return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId, @Valid @RequestBody Employee employeeDetails) throws
            ResourceNotFountException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFountException(
                "Employee not found for this id : " + employeeId
        ));
        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/employee/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value="id") Long employeeId)
        throws ResourceNotFountException{
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFountException("Employee not found for this id: " + employeeId));
        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}

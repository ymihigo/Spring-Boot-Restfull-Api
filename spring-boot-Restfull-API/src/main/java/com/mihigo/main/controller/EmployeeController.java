package com.mihigo.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mihigo.main.exception.Exceptions;
import com.mihigo.main.model.Employee;
import com.mihigo.main.repository.EmployeeRepository;


@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepo;

	// Get All Employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return empRepo.findAll();
	}

	// create new Employee
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		return empRepo.save(emp);
	}

	// get Employee Id
	@PostMapping("/employees/{id}")
	public ResponseEntity<Employee> findById(@PathVariable(value = "id") int id ) throws Exceptions{
		Employee empl = empRepo.findById(id).orElseThrow(() -> new Exceptions("Employee not found for this id :: " + id));
		return ResponseEntity.ok().body(empl);
	}
	//update employee
	
	@PostMapping("employeesU/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") int id, @Valid @RequestBody Employee employee) throws Exceptions{
		Employee emp=empRepo.findById(id).orElseThrow(() -> new Exceptions("employee not found"));
		
		emp.setLname(employee.getLname());
		emp.setFname(employee.getFname());
		emp.setEmail(employee.getEmail());
		
		final Employee UpdatedEmployee=empRepo.save(emp);
		return ResponseEntity.ok(UpdatedEmployee);
	}
	
	//Delete Employee
	
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") int id)
			throws Exceptions {
		Employee employee = empRepo.findById(id)
				.orElseThrow(() -> new Exceptions("Employee not found for this id :: " + id));

		empRepo.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}

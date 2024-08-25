package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@PostMapping("/employees")
	public ResponseEntity<String> createNewEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);
		return new ResponseEntity<>("Employee Created in database", HttpStatus.CREATED);
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> empList = employeeRepository.findAll();
		if (empList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(empList, HttpStatus.OK);
	}

	@GetMapping("/employees/{empid}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long empid) {
		Optional<Employee> emp = employeeRepository.findById(empid);
		if (emp.isPresent()) {
			return new ResponseEntity<>(emp.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/employees/{empid}")
	public ResponseEntity<String> updateEmployeeById(@PathVariable long empid, @RequestBody Employee employee) {
		Optional<Employee> emp = employeeRepository.findById(empid);
		if (emp.isPresent()) {
			Employee existEmp = emp.get();
			existEmp.setEmpage(employee.getEmpage());
			existEmp.setEmpcity(employee.getEmpcity());
			existEmp.setEmpname(employee.getEmpname());
			existEmp.setEmpsalary(employee.getEmpsalary());
			employeeRepository.save(existEmp);
			return new ResponseEntity<>("Employee Details against Id " + empid + " updated", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Employee Details do not exist for empid " + empid, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/employees/{empid}")
	public ResponseEntity<String> deleteEmployeeByEmpId(@PathVariable Long empid) {
		Optional<Employee> emp = employeeRepository.findById(empid);
		if (emp.isPresent()) {
			employeeRepository.deleteById(empid);
			return new ResponseEntity<>("Employee Deleted Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/employees")
	public ResponseEntity<String> deleteAllEmployees() {
		employeeRepository.deleteAll();
		return new ResponseEntity<>("All Employees Deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("/employees/empcity")
	public ResponseEntity<Employee> getEmployeeByCity(@RequestParam("emp_city") String emp_city) {
		Employee emp = employeeRepository.findByEmpcity(emp_city);
		if (emp != null) {
			return new ResponseEntity<>(emp, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/employees/ageGreaterThan")
	public ResponseEntity<List<Employee>> getEmployeesGreaterThan(@RequestParam("emp_age") int emp_age) {
		Optional<List<Employee>> empList = employeeRepository.findByEmpageGreaterThan(emp_age);
		if (empList.isPresent() && !empList.get().isEmpty()) {
			return new ResponseEntity<>(empList.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}

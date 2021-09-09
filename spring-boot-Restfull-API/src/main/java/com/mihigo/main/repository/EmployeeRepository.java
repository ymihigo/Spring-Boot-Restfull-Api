package com.mihigo.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mihigo.main.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
//	public List<Empl>

}

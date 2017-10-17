package com.erp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.Department;
import java.lang.String;
import java.util.List;

@Repository
public interface DepartmentRepo extends CrudRepository<Department, Integer> {
	List<Department> findByName(String name);
}
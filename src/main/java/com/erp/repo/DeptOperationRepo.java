package com.erp.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.Department;
import com.erp.classes.DeptOperationDetails;
import java.lang.String;

@Repository
public interface DeptOperationRepo extends CrudRepository<DeptOperationDetails, Integer> {

	List<DeptOperationDetails> findByName(String name);

	List<DeptOperationDetails> findByDept(Department dept_id);

	List<DeptOperationDetails> findByColor(String color);
}
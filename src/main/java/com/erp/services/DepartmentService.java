package com.erp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.classes.Department;
import com.erp.repo.DepartmentRepo;

@Service
public class DepartmentService {
	@Autowired
	public DepartmentRepo repo;

	List<Department> departmentList = new ArrayList<Department>();

	public void save(Department Department) {
		repo.save(Department);

	}

	public void delete(int id) {
		repo.delete(id);

	}

	public void update(int id, Department Department) {
		repo.save(Department);

	}

	public Department find(int id) {
		System.out.println(repo.findOne(id));
		return repo.findOne(id);

	}

	public List<Department> getAll() {
		departmentList.clear();
		repo.findAll().forEach(departmentList::add);
		return departmentList;

	}
	public List<Department> findByName(String dept) {
		return repo.findByName(dept);

	}

}

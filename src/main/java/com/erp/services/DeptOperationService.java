package com.erp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.classes.Department;
import com.erp.classes.DeptOperationDetails;
import com.erp.repo.DeptOperationRepo;

@Service
public class DeptOperationService {
	@Autowired
	public DeptOperationRepo repo;

	List<DeptOperationDetails> deptODList = new ArrayList<DeptOperationDetails>();

	public void save(DeptOperationDetails DeptOperationDetails) {
		repo.save(DeptOperationDetails);

	}

	public void delete(int id) {
		repo.delete(id);

	}

	public void update(int id, DeptOperationDetails DeptOperationDetails) {
		repo.save(DeptOperationDetails);

	}

	public DeptOperationDetails find(int id) {
		System.out.println(repo.findOne(id));
		return repo.findOne(id);

	}

	public List<DeptOperationDetails> getAll() {
		deptODList.clear();
		repo.findAll().forEach(deptODList::add);
		return deptODList;

	}
	public List<DeptOperationDetails> findByDept(Department dept) {
		
		return repo.findByDept(dept);

	}

}

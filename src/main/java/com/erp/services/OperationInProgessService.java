package com.erp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.classes.DeptOperationDetails;
import com.erp.classes.OperationInProgress;
import com.erp.classes.OrderDetail;
import com.erp.repo.OperationInProgressRepo;

@Service
public class OperationInProgessService {
	@Autowired
	private OperationInProgressRepo repo;

	List<OperationInProgress> operationInProgressList = new ArrayList<OperationInProgress>();

	public void save(OperationInProgress OperationInProgress) {
		repo.save(OperationInProgress);

	}

	public void delete(int id) {
		repo.delete(id);

	}

	public void update(int id, OperationInProgress OperationInProgress) {
		repo.save(OperationInProgress);

	}

	public OperationInProgress find(int id) {
		System.out.println(repo.findOne(id));
		return repo.findOne(id);

	}

	public List<OperationInProgress> getAll() {
		operationInProgressList.clear();
		repo.findAll().forEach(operationInProgressList::add);
		return operationInProgressList;
	}

	public List<OperationInProgress> findByOrderDetail(OrderDetail orderdetail) {
		return repo.findByOrderDetail(orderdetail);
	}

	public List<OperationInProgress> findByDeptODAndOrderDetail(DeptOperationDetails deptOD, OrderDetail orderDetail) {
		return repo.findByDeptODAndOrderDetail(deptOD, orderDetail);
	}

	public List<OperationInProgress> findByDeptOD(DeptOperationDetails deptod) {
		return repo.findByDeptOD(deptod);
	}

	public List<OperationInProgress> findByDeptODAndStatus(DeptOperationDetails deptod, String status) {
		return repo.findByDeptODAndStatus(deptod, status);
	}
}

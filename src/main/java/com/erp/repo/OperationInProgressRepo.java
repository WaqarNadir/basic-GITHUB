package com.erp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.OperationInProgress;
import com.erp.classes.OrderDetail;
import java.util.List;
import com.erp.classes.DeptOperationDetails;

@Repository
public interface OperationInProgressRepo extends JpaRepository<OperationInProgress, Integer> {
	List<OperationInProgress> findByOrderDetail(OrderDetail orderdetail);

	List<OperationInProgress> findByDeptODAndOrderDetail(DeptOperationDetails deptod, OrderDetail od);

	List<OperationInProgress> findByDeptODAndStatus(DeptOperationDetails deptod,String status);
	List<OperationInProgress> findByDeptOD(DeptOperationDetails deptod);
	}
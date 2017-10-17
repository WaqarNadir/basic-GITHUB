package com.erp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.OperationInProgress;
import com.erp.classes.OrderDetail;
import java.util.List;

@Repository
public interface OperationInProgressRepo extends CrudRepository<OperationInProgress, Integer> {
	List<OperationInProgress> findByOrderDetail(OrderDetail orderdetail);

}
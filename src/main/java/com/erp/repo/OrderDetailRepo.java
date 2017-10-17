package com.erp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.OrderDetail;
import com.erp.classes.Order;
import java.util.List;

@Repository
public interface OrderDetailRepo extends CrudRepository<OrderDetail, Integer> {
	List<OrderDetail> findByOrder(Order order);

}
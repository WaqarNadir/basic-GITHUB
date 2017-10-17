package com.erp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.Order;
import java.lang.String;
import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<Order, Integer> {
	Integer LastPKValue();
	List<Order> findByOrderStatus(String orderstatus);

}
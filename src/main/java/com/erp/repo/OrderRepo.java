package com.erp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.Order;
import java.lang.String;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
	Integer LastPKValue();

	List<Order> findByOrderStatus(String orderstatus);

	List<Order> findByorderStatusNotLike(String orderstatus);

}
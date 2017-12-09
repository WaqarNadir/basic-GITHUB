package com.erp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.OrderDetail;
import com.erp.classes.Order;
import java.util.List;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer> {
	List<OrderDetail> findByOrder(Order order);
	List<OrderDetail> findByMachineID(int machineID);

}
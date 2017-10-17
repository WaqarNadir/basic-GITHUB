package com.erp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.classes.Order;
import com.erp.classes.OrderDetail;
import com.erp.repo.OrderDetailRepo;

@Service
public class OrderDetailService {

	@Autowired
	private OrderDetailRepo repo;

	List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();

	public void save(OrderDetail OrderDetail) {
		repo.save(OrderDetail);

	}

	public void delete(int id) {
		repo.delete(id);

	}

	public void update(int id, OrderDetail OrderDetail) {
		repo.save(OrderDetail);

	}

	public OrderDetail find(int id) {
		return repo.findOne(id);

	}

	public List<OrderDetail> getAll() {
		orderDetailList.clear();
		repo.findAll().forEach(orderDetailList::add);
		return orderDetailList;
	}
	public List<OrderDetail> findByOrder(Order order ) {
		
		return repo.findByOrder(order);
	}
}

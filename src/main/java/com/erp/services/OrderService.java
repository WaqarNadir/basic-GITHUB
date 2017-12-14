package com.erp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.classes.Order;
import com.erp.repo.OrderRepo;

@Service
public class OrderService {

	@Autowired
	private OrderRepo repo;
	List<Order> orderList = new ArrayList<Order>();

	public void save(Order Order) {
		repo.save(Order);

	}

	public void saveAndFlush(Order Order) {
		repo.saveAndFlush(Order);

	}

	public void delete(int id) {
		repo.delete(id);

	}

	public int lastPKValue() {
		Integer value = repo.LastPKValue();
		if (value == null)
			return 0;
		return value;

	}

	public void update(int id, Order Order) {
		repo.save(Order);

	}

	public Order find(int id) {
		System.out.println(repo.findOne(id));
		return repo.findOne(id);

	}

	public List<Order> getAll() {
		orderList.clear();
		repo.findAll().forEach(orderList::add);
		return orderList;

	}

	public List<Order> findByStatus(String orderStatus) {
		return repo.findByOrderStatus(orderStatus);

	}

	public List<Order> findByStatusNotLike(String orderStatus) {
		return repo.findByorderStatusNotLike((orderStatus));

	}

}

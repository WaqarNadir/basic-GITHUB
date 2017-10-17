package com.erp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.erp.classes.OrderDetail;
import com.erp.services.OrderDetailService;

@RestController
public class OrderDetailController {
	
	@Autowired
	private OrderDetailService service;
	
	@RequestMapping("OrderDetail")
	public List<OrderDetail> getAll(){
			return service.getAll();
		
	}
	@RequestMapping(method=RequestMethod.POST,value="/NewOrderDetail")
	public void update(@RequestBody OrderDetail OrderDetail) {
		service.save(OrderDetail);
		
	}
	@RequestMapping(method=RequestMethod.PUT,value="/UpdateOrderDetail/{id}")
	public void update(@RequestBody OrderDetail OrderDetail,@PathVariable int id) {
		service.update(id, OrderDetail);
		
	}
	@RequestMapping(method=RequestMethod.DELETE,value="/OrderDetail/{id}")
	public void delete(@PathVariable int id) {
		service.delete(id);
		
	}
	@RequestMapping(method=RequestMethod.GET,value="/OrderDetail/{id}")
	public OrderDetail GetOrder(@PathVariable int id) {
		return service.find(id);
		
	}
	

}

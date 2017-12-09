//package com.erp.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.erp.classes.DeptOperationDetails;
//import com.erp.classes.OperationInProgress;
//import com.erp.classes.Order;
//import com.erp.classes.OrderDetail;
//import com.erp.classes.Person;
//import com.erp.classes.Product;
//import com.erp.services.DepartmentService;
//import com.erp.services.DeptOperationService;
//import com.erp.services.OperationInProgessService;
//import com.erp.services.OrderDetailService;
//
//@RestController
//public class OrderDetailController {
//
//	@Autowired
//	private OrderDetailService service;
//	@Autowired
//	private DepartmentService DeptService;
//	
//	@Autowired
//	private DeptOperationService operationService;
//	@Autowired
//	private OperationInProgessService OIPservice;
//
//	List<DeptOperationDetails> operationsList;
//	List<OperationInProgress> OIPList;
//	List<Product> productlist;
//	List<Product> subType;
//	List<Person> personList;
//	List<OrderDetail> orderDetailList;
//
//	Product selectedProduct;
//	Order savedOrder;
//
//	@GetMapping("orderDetails")
//	public String OrderDetail(Model model) {
//		model.addAttribute("ODList", orderDetailList);
//		model.addAttribute("order", savedOrder);
//		operationsList = operationService.getAll();
//		model.addAttribute("operationList", operationsList);
//		model.addAttribute("dept", DeptService.getAll());
//
//		return "OrderDetail";
//	}
//	
//	public List<OrderDetail> findByOrder(){
//		
//	}
//
//	// @RequestMapping("OrderDetail")
//	// public List<OrderDetail> getAll(){
//	// return service.getAll();
//	//
//	// }
//	// @RequestMapping(method=RequestMethod.POST,value="/NewOrderDetail")
//	// public void update(@RequestBody OrderDetail OrderDetail) {
//	// service.save(OrderDetail);
//	//
//	// }
//	// @RequestMapping(method=RequestMethod.PUT,value="/UpdateOrderDetail/{id}")
//	// public void update(@RequestBody OrderDetail OrderDetail,@PathVariable int id)
//	// {
//	// service.update(id, OrderDetail);
//	//
//	// }
//	// @RequestMapping(method=RequestMethod.DELETE,value="/OrderDetail/{id}")
//	// public void delete(@PathVariable int id) {
//	// service.delete(id);
//	//
//	// }
//	// @RequestMapping(method=RequestMethod.GET,value="/OrderDetail/{id}")
//	// public OrderDetail GetOrder(@PathVariable int id) {
//	// return service.find(id);
//	//
//	// }
//
//}

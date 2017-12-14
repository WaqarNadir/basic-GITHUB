package com.erp.controllers;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.classes.DeptOperationDetails;
import com.erp.classes.OperationInProgress;
import com.erp.classes.Order;
import com.erp.classes.OrderDetail;
import com.erp.classes.Person;
import com.erp.classes.Product;
import com.erp.services.DepartmentService;
import com.erp.services.DeptOperationService;
import com.erp.services.OperationInProgessService;
import com.erp.services.OrderDetailService;
import com.erp.services.OrderService;

@Controller
public class OrderDetailController {

	@Autowired
	private OrderDetailService service;
	@Autowired
	private OrderService orderService;
	@Autowired
	private DepartmentService DeptService;

	@Autowired
	private DeptOperationService operationService;
	@Autowired
	private OperationInProgessService OIPservice;

	List<DeptOperationDetails> operationsList;
	List<OperationInProgress> OIPList;
	List<Product> productlist;
	List<Product> subType;
	List<Person> personList;
	List<OrderDetail> orderDetailList;

	Product selectedProduct;

	@GetMapping(value = "viewOrder/orderDetail/{id}")
	public String GetDetails(@PathVariable("id") int id, Model model) {
		Order order = orderService.find(id);
		orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList = service.findByOrder(order);

		model.addAttribute("ODList", orderDetailList);
		model.addAttribute("order", order);
		OIPList = new ArrayList<>();
		for (OrderDetail OD : orderDetailList) {
			OIPList.addAll(OIPservice.findByOrderDetail(OD));
		}

		model.addAttribute("OIPList", OIPList);
		// model.addAttribute("dept", DeptService.getAll());

		return "OrderDetail";
	}

}

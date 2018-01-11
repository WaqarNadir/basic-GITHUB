package com.erp.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.apache.catalina.connector.Response;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	private OperationInProgessService OIPservice;

	// @Autowired
	// private DepartmentService DeptService;
	//
	// @Autowired
	// private DeptOperationService operationService;

	// List<DeptOperationDetails> operationsList;

	List<OperationInProgress> OIPList;
	List<Person> personList;
	List<OrderDetail> orderDetailList;
	List<Order> orderList;

	@GetMapping("/ViewOrder")
	public String getAll(Model model) {
		orderList = new ArrayList<>();
		orderList = orderService.getAll();
		orderList.sort(Comparator.comparing(Order::getOrderStatus));
		model.addAttribute("orderList", orderList);
		return "ViewAllOrder";
	}

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

	@PostMapping(value = "/viewOrder/swap")
	public @ResponseBody int swap(String ID, String swapWith) {
		int orderID;
		Order firstOrder;
		if (ID != null && swapWith != null)
			orderID = Integer.parseInt(ID);
		else
			return Response.SC_BAD_REQUEST;

		firstOrder = getOrder(orderID);
		for (Order val : orderList) {
			if (val.getSequenceNo().equals(swapWith)) {
				val.setSequenceNo(firstOrder.getSequenceNo());
				firstOrder.setSequenceNo(swapWith);
				orderService.save(firstOrder);
				orderService.save(val);
				return val.getOrder_ID();
			}

		}
		return Response.SC_NOT_FOUND;
	}

	public Order getOrder(int id) {
		if (orderList == null)
			return orderService.find(id);
		for (Order ord : orderList) {
			if (ord.getOrder_ID() == id) {
				return ord;
			}
		}
		return null;
	}

}

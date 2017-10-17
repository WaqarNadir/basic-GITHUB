package com.erp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erp.classes.DailyProduction;
import com.erp.classes.Department;
import com.erp.classes.DeptOperationDetails;
import com.erp.classes.OperationInProgress;
import com.erp.classes.Order;
import com.erp.classes.OrderDetail;
import com.erp.services.DailyProductionService;
import com.erp.services.DepartmentService;
import com.erp.services.DeptOperationService;
import com.erp.services.OperationInProgessService;
import com.erp.services.OrderDetailService;
import com.erp.services.OrderService;

@Controller
public class OIPController {

	@Autowired
	private OperationInProgessService service;
	@Autowired
	private OrderService orderSrvice;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private DepartmentService deptService;
	@Autowired
	private DeptOperationService deptOPService;
	@Autowired
	private OperationInProgessService OIPService;

	List<Order> orderList = null;
	List<OrderDetail> selectedODList;
	int ODListCounter = 0;
	List<Department> DeptList;
	List<DeptOperationDetails> selectedOperationsList;
	int operationListCounter = 0;

	@GetMapping("NewOIP")
	public String home(Model model) {
		selectedODList = new ArrayList<OrderDetail>();
		ODListCounter = 0;
		operationListCounter = 0;
		selectedOperationsList = new ArrayList<DeptOperationDetails>();
		model.addAttribute("OIP", new OperationInProgress());
		model.addAttribute("orderList", getOrderList());
		model.addAttribute("deptList", getDepartment());

		return "NewOIP";
	}

	@GetMapping("ReportDP")
	public String abc(Model model) {
			return "ReportDP";
	}

	@PostMapping("OIP/save")
	public String save(@ModelAttribute("OIP") OperationInProgress OIP) {
		if (OIP != null) {
			;
			OIP.setOrderDetail(selectedODList.get(Integer.parseInt(OIP.getOrderDetail().getRemarks())));
			OIP.setDeptOD_ID(selectedOperationsList.get(Integer.parseInt(OIP.getDeptOD_ID().getName())));

			service.save(OIP);
		}
		return "redirect:/NewDP";
	}

	@PostMapping("OIP/operationList")
	public @ResponseBody List<String> getOperations(@RequestBody int id) {

		Department dept = deptService.find(id);

		List<String> operations = new ArrayList<String>();
		for (DeptOperationDetails deptOP : deptOPService.findByDept(dept)) {
			selectedOperationsList.add(operationListCounter, deptOP);
			operations.add(deptOP.getName() + "," + operationListCounter);
			operationListCounter++;
		}
		return operations;
	}

	@PostMapping("OIP/productList")
	public @ResponseBody List<String> getProductList(@RequestBody int orderID) {
		List<String> result = new ArrayList<String>();

		Order order = getOrder(orderID);

		// this list will duplicate value . should check from selected list first
		List<OrderDetail> orderDetail = new ArrayList<OrderDetail>();
		orderDetail = orderDetailService.findByOrder(order);

		for (OrderDetail OD : orderDetail) {
			if (OD != null && OD.getProdDetail() != null && OD.getProdDetail().getProduct() != null) {

				result.add(OD.getProdDetail().getProduct().getName() + " | " + OD.getConstruction()
				// + " | " + OD.get(i).getProdDetail().getDesignNo() + " | "
						+ " | " + OD.getProdDetail().getColor());

				selectedODList.add(ODListCounter, OD);
				ODListCounter++;

			} // will pass lotno , index to get value later and

		}

		return result;
		// must return string
	}

	public List<Order> getOrderList() {
		orderList = orderSrvice.findByStatus("open");
		return orderList;
	}

	public List<Department> getDepartment() {
		if (DeptList == null)
			DeptList = deptService.getAll();
		return DeptList;
	}

	private Order getOrder(int orderID) {

		for (Order ord : orderList) {
			if (ord.getOrder_ID().equals(orderID)) {
				// selectedODList.clear();
				return ord;
			}
		}
		return null;
	}

}

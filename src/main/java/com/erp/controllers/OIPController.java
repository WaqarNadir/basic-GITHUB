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
import org.springframework.web.servlet.ModelAndView;

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

	List<Order> orderList = null;
	List<OrderDetail> selectedODList;
	List<DeptOperationDetails> selectedOperationsList;
	// int ODListCounter = 0;
	List<Department> DeptList;

	// int operationListCounter = 0;

	@GetMapping("NewOIP")
	public String home(Model model) {
		selectedODList = new ArrayList<OrderDetail>();
		// ODListCounter = 0;
		// operationListCounter = 0;
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
			OrderDetail OD = getFromSelectedOrderList(Integer.parseInt(OIP.getOrderDetail().getRemarks()));

			OIP.setOrderDetail(OD);
			OIP.setDeptOD(getFromSelectedOperationList(Integer.parseInt(OIP.getDeptOD().getName())));
			OIP.setExpectedEndDate(OD.getExpectedEndDate());

			service.save(OIP);
		}
		return "redirect:/NewDP";
	}

	@PostMapping("OIP/operationList")
	public @ResponseBody List<String> getOperations(@RequestBody String data) {
		// operationListCounter = selectedOperationsList.size();
		String[] input = data.split(",");
		int id = Integer.parseInt(input[0]);
		int orderID = Integer.parseInt(input[1]);
		String prodName = input[2].trim();

		Department dept = deptService.find(id);
		List<String> operations = new ArrayList<String>();

		if (dept.getName().equals("Printing")) {
			for (OrderDetail OD : selectedODList) {
				if (OD.getProdDetail().getProduct().getName().equals(prodName)
						&& OD.getOrder().getOrder_ID() == orderID) {
					DeptOperationDetails deptOP = deptOPService.find(OD.getMachineID());
					operations.add(deptOP.getName() + "," + deptOP.getDeptOD_ID());
					// operationListCounter++;
					selectedOperationsList.add(deptOP);
				}

			}
		} else {

			for (DeptOperationDetails deptOP : deptOPService.findByDept(dept)) {
				selectedOperationsList.add(deptOP);
				operations.add(deptOP.getName() + "," + deptOP.getDeptOD_ID());
				// operationListCounter++;
			}

		}
		return operations;
	}

	@PostMapping("OIP/productList")
	public @ResponseBody List<String> getProductList(@RequestBody int orderID) {
		List<String> result = new ArrayList<String>();

		Order order = getOrder(orderID);

		List<OrderDetail> orderDetail = new ArrayList<OrderDetail>();
		orderDetail = orderDetailService.findByOrder(order);

		for (OrderDetail OD : orderDetail) {
			if (OD != null && OD.getProdDetail() != null && OD.getProdDetail().getProduct() != null) {

				result.add(OD.getProdDetail().getProduct().getName() + " | " + OD.getConstruction()
				// + " | " + OD.get(i).getProdDetail().getDesignNo() + " | "
						+ " | " + OD.getProdDetail().getColor());

				selectedODList.add(OD);
				// ODListCounter++;

			} // will pass lotno , index to get value later and

		}

		return result;
		// must return string
	}

	@PostMapping("/example-url")
	public ModelAndView example(@RequestBody Integer ID) {

		ModelAndView view = new ModelAndView("OIPFragments  :: ProductDetail");
		Order order = getOrder(ID);

		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList = orderDetailService.findByOrder(order);
		selectedODList.addAll(orderDetailList);
		view.addObject("orderDetailList", orderDetailList);

		return view;
	}

	private OrderDetail getFromSelectedOrderList(int id) {

		for (OrderDetail OD : selectedODList) {
			if (OD.getOrdDetail_ID() == id) {
				return OD;
			}
		}
		return null;
	}

	private DeptOperationDetails getFromSelectedOperationList(int id) {

		for (DeptOperationDetails DeptOD : selectedOperationsList) {
			if (DeptOD.getDeptOD_ID() == id) {
				return DeptOD;
			}
		}
		return null;
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

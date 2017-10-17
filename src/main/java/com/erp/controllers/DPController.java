package com.erp.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.classes.DailyProduction;
import com.erp.classes.Department;
import com.erp.classes.DeptOperationDetails;
import com.erp.classes.OperationInProgress;
import com.erp.classes.Order;
import com.erp.classes.OrderDetail;
import com.erp.classes.functions;
import com.erp.services.DailyProductionService;
import com.erp.services.DepartmentService;
import com.erp.services.DeptOperationService;
import com.erp.services.OperationInProgessService;
import com.erp.services.OrderDetailService;
import com.erp.services.OrderService;

@Controller
public class DPController {

	@Autowired
	private DailyProductionService service;

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
	List<OperationInProgress> OIPList;

	@GetMapping("/NewDP")
	public String DailyProductionHome(Model model) {
		selectedODList = new ArrayList<OrderDetail>();
		operationListCounter=0;
		ODListCounter = 0;
		selectedOperationsList = new ArrayList<DeptOperationDetails>();
		model.addAttribute("OIP", new OperationInProgress());
		model.addAttribute("DP", new DailyProduction());
		model.addAttribute("orderList", getOrderList());
		model.addAttribute("deptList", getDepartment());
		return "DailyProduction";
	}

	@PostMapping("DP/save")
	public String  save(@RequestBody String[] dataList) {
		OrderDetail OD = new OrderDetail();
		DailyProduction DP = new DailyProduction();
		// OperationInProgress OIP = new OperationInProgress();

		OIPList = getAll();
		for (String data : dataList) {
			String[] result = data.split(",");
			
			int operationIndex = Integer.parseInt(result[0]);
			int productIndex = Integer.parseInt(result[1]);

			double InitialCloth = Integer.parseInt(result[2]);
			double openingStock = Double.parseDouble(result[3]);
			double dailyProduction = Double.parseDouble(result[4]);
			double closingStock = openingStock - dailyProduction;

			Date date = functions.convertDate(result[6]);

			OD = selectedODList.get(productIndex);
			DP.setOpeningStock(openingStock);
			DP.setDailyProduction(dailyProduction);
			DP.setClosingStock(closingStock);
			DP.setWorkStatus(result[5]);
			DP.setDate(date);

			for (OperationInProgress OIP : OIPList) {

				if (OIP.getOrderDetail().getOrdDetail_ID() == OD.getOrdDetail_ID() && OIP.getDeptOD_ID()
						.getDeptOD_ID() == selectedOperationsList.get(operationIndex).getDeptOD_ID()) {
					DP.setOIP_ID(OIP);

					service.save(DP);

				}

			}

		}
		return "redirect:/NewDP";
	}
	
	
	@GetMapping("")

	@PostMapping("getProductDetail")
	public @ResponseBody double[] getQuantity(@RequestBody String data) {
		String[] requestData = data.split("!");
		double[] result = new double[2];
		OrderDetail OD = new OrderDetail();

		OD = selectedODList.get(Integer.parseInt(requestData[0]));
		DeptOperationDetails DeptOD = selectedOperationsList.get(Integer.parseInt(requestData[1]));
		OIPList = new ArrayList<>();
		OIPList.addAll(OIPService.findByOrderDetail(OD));
		result[0] = OD.getQuantity();

		for (OperationInProgress OIP : OIPList) {
			if (OIP != null && DeptOD.getDeptOD_ID() == OIP.getDeptOD_ID().getDeptOD_ID()) {
				result[1] = OIP.getInitialCloth();
			}
		}

		return result;
	}

	@PostMapping("/operationList")
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

	@PostMapping("productList")
	public @ResponseBody List<String> getProductList(@RequestBody int orderID) {
		List<String> result = new ArrayList<String>();

		Order order = getOrder(orderID);

		// this list will duplicate value . should check from selected list first
		List<OrderDetail> orderDetail = new ArrayList<OrderDetail>();
		orderDetail = orderDetailService.findByOrder(order);

		for (OrderDetail OD : orderDetail) {
			if (OD != null && OD.getProdDetail() != null && OD.getProdDetail().getProduct() != null) {

				result.add(OD.getOrder().getLotNo() + "," + ODListCounter + ","
						+ OD.getProdDetail().getProduct().getName() + " | " + OD.getConstruction()
						// + " | " + OD.get(i).getProdDetail().getDesignNo() + " | "
						+ OD.getProdDetail().getColor());

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

	public List<OperationInProgress> getAll() {
		return OIPService.getAll();
	}

}

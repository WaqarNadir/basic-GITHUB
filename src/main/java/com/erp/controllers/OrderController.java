package com.erp.controllers;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.erp.classes.Constants;
import com.erp.classes.Department;
import com.erp.classes.DeptOperationDetails;
import com.erp.classes.OperationInProgress;
import com.erp.classes.Order;
import com.erp.classes.OrderDetail;
import com.erp.classes.OrderDetailWrapperClass;
import com.erp.classes.Person;
import com.erp.classes.Product;
import com.erp.classes.ProductDetail;
import com.erp.classes.functions;
import com.erp.services.DepartmentService;
import com.erp.services.DeptOperationService;
import com.erp.services.OperationInProgessService;
import com.erp.services.OrderDetailService;
import com.erp.services.OrderService;
import com.erp.services.PersonService;
import com.erp.services.ProductService;

@Controller
@SessionAttributes(value = "productDetail", types = { ProductDetail.class })
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private PersonService personService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private ProductService ProdService;

	List<Product> productlist;
	List<Product> subType;
	List<Person> personList;
	List<OrderDetail> orderDetailList;

	Product selectedProduct;
	Order savedOrder;
	DecimalFormat formatter = new DecimalFormat("00");
	// Constants constant;
	functions function;
	int prodType = 0;

	// ----------------------
	@Autowired
	private DepartmentService DeptService;
	@Autowired
	private DeptOperationService operationService;
	@Autowired
	private OperationInProgessService OIPservice;

	List<DeptOperationDetails> operationsList;
	List<OperationInProgress> OIPList;
	Date currentDate;
	Date lastScheduledOrderDate;

	@GetMapping(value = "/NewOrder")
	public String NewOrder(Model model) {
		init();
		getseqNo();
		OrderDetailWrapperClass wrapper = new OrderDetailWrapperClass();
		Order order = new Order();
		order.setLotNo(getLotNo());
		order.setRefNo(getRefNo());
		order.setDate(currentDate);

		wrapper.setOrder(order);
		model.addAttribute("wrapper", wrapper);

		model.addAttribute("productList", getProductList(1));
		model.addAttribute("personList", getPerson());

		return "NewOrderTest";
	}

	private void init() {
		operationsList = new ArrayList<DeptOperationDetails>();
		OIPList = new ArrayList<OperationInProgress>();
		currentDate = new Date(System.currentTimeMillis());
		personList = new ArrayList<Person>();
		subType = new ArrayList<Product>();
	}

	@PostMapping(value = "Order/save")
	public String save(@ModelAttribute OrderDetailWrapperClass data, Errors errors) {
		OrderDetail OD;
		if (errors.hasErrors() || data.getOrderDetailList().parallelStream()
				.filter(item -> item.getMachineID().equals(999)).count() > 0) {
			System.out.println(errors.toString());
		}

		if (data.getOrderDetailList().isEmpty())
			return "/NewOrder";

		OD = data.getOrderDetailList().get(0);
		data.getOrder().setSequenceNo(getseqNo());

		data.getOrderDetailList().removeIf(item -> {
			if (item.getMachineID() == null || item.getMachineID() == 999)
				return true;
			return false;
		});

		data.getOrderDetailList().stream().skip(1)
				.forEach(orderDetail -> orderDetail.copyValue(data.getOrderDetailList().get(0)));

		List<Product> subproducts = getSubType(OD.getProdDetail().getProduct());

		for (int i = 0; i < data.getOrderDetailList().size(); i++) {
			OD = data.getOrderDetailList().get(i);
			OD.setOrder(data.getOrder());
			OD.getProdDetail().setProduct(subproducts.get(i));
			OD.getOrder().setOrderStatus(Constants.open);
			orderDetailService.save(OD);
		}
		return "redirect:/NewOrder";

	}

	private String getseqNo() {
		String seqNo = orderService.getAll().stream().max(Comparator.comparing(Order::getSequenceNo)).get()
				.getSequenceNo();
		if (seqNo == null)
			return 1 + "";
		seqNo = "" + (Integer.parseInt(seqNo) + 1);
		return seqNo;
	}

	@GetMapping("orderDetails")
	public String OrderDetail(Model model) {
		model.addAttribute("ODList", orderDetailList);
		model.addAttribute("order", savedOrder);
		operationsList = operationService.getAll();
		model.addAttribute("operationList", operationsList);
		model.addAttribute("dept", DeptService.getAll());

		return "OrderDetail";
	}

	@PostMapping(value = "getProdType")
	public @ResponseBody List<String> getProdType(@Valid @RequestBody String product) {
		List<String> result = new ArrayList<>();

		for (Product prod : productlist) {
			if (prod.getProd_ID().toString().equals(product))
				result.add(prod.getProdType());
		}
		return result;
	}

	@PostMapping(value = "getMachine")
	public @ResponseBody List<String> getOperation(@RequestBody String color) {
		List<String> result = new ArrayList<>();
		Department dept = DeptService.findByName("Printing").get(0);
		operationsList = operationService.findByDept(dept);
		operationsList.sort(Comparator.comparing(DeptOperationDetails::getColor));

		for (DeptOperationDetails deptOD : operationsList) {
			// deptOD != null &&
			if (deptOD.getColor() >= Integer.parseInt(color)) {
				String formattedValue = formatter.format(deptOD.getColor());
				result.add(formattedValue + " Colors " + "|" + " " + deptOD.getName() + "," + deptOD.getDeptOD_ID());
			}
		}

		return result;

	}

	@PostMapping(value = "getEndDate")
	public @ResponseBody List<String[]> getEndDate(@RequestBody String data) {
		String[] input = data.split("@");
		String machineName = input[0];
		String[] result = new String[5];
		List<String[]> resultList = new ArrayList<String[]>();

		for (DeptOperationDetails deptOD : operationsList) {
			if (deptOD.getName().equalsIgnoreCase(machineName)) {

				OIPList = OIPservice.findByDeptODAndStatus(deptOD, Constants.Active);
				OIPList.sort(Comparator.comparing(OperationInProgress::getExpectedEndDate));
				if (!OIPList.isEmpty()) {

					for (OperationInProgress OIP : OIPList) {
						result[0] = OIP.getExpectedEndDate().toString();
						result[1] = OIP.getOrderDetail().getOrder().getRefNo();
						result[2] = computeOrderEndDate(deptOD, input[1], OIP.getExpectedEndDate());
						result[3] = Constants.inProgress;
						resultList.add(result);
						System.err.println("Order Number  : " + result[1] + " ---- Order Date " + result[2]);

						// return resultList;
					}
				} else {
					result[3] = Constants.open;
					result[2] = computeOrderEndDate(deptOD, input[1], currentDate);
					result[1] = result[0] = currentDate.toString();
					resultList.add(result);
					System.err.println("Is Open " + " ---- Order Date " + result[2]);
				}
				resultList.addAll(getScheduledOrder(deptOD));
			}
		}

		return resultList;

	}

	public List<String[]> getScheduledOrder(DeptOperationDetails deptOD) {

		List<String[]> resultList = new ArrayList<String[]>();
		List<OrderDetail> ODList = orderDetailService.findByMachineID(deptOD.getDeptOD_ID());
		ODList.sort(Comparator.comparing(OrderDetail::getExpectedEndDate));
		for (OrderDetail OD : ODList) {
			String result[] = new String[4];

			if (OD.getExpectedEndDate() != null)
				result[0] = OD.getExpectedEndDate().toString();

			result[1] = OD.getOrder().getRefNo();
			result[2] = OD.getExpectedEndDate().toString();
			result[3] = Constants.scheduled;
			resultList.add(result);
			System.err.println("Order Number  : " + result[1] + "---- Order Date " + result[2]);
			lastScheduledOrderDate = OD.getExpectedEndDate();
		}
		return resultList;
	}

	public String computeOrderEndDate(DeptOperationDetails deptOD, String orderQuantity, Date StartDate) {
		double baseProduction = deptOD.getBaseProduction();
		double quantity = Double.parseDouble(orderQuantity);
		int days = (int) Math.ceil(quantity / baseProduction);
		return days + "/" + function.addDays(days, StartDate);

	}

	protected List<Product> getProductList(int isFinal) {
		productlist = ProdService.findFinalProduct(isFinal);
		return productlist;
	}

	public List<Product> getSubType(Product prod) {
		return ProdService.findByParentRef(prod.getProd_ID());

	}

	public List<Person> getPerson() {

		personList = personService.getAll();
		return personList;

	}

	public String getLotNo() {

		String lotNo = "LN-01" + String.format("%05d", (getOrderID() + 1));
		// will be changed to XML parameter
		return lotNo;
	}

	public String getRefNo() {
		String refNo = "MO-01" + String.format("%05d", (getOrderID() + 1)); // 5 digit formated
		// will be changed to XML parameter

		return refNo;
	}

	private int getOrderID() {
		return orderService.lastPKValue();

	}

}

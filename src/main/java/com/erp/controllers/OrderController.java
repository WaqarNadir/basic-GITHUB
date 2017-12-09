package com.erp.controllers;

import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.erp.services.PersonDetialService;
import com.erp.services.PersonService;
import com.erp.services.ProductDetailService;
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
	// @Autowired
	// private ProductDetailService prodDetailService;

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
	Date LastScheduledOrder;

	@GetMapping(value = "/NewOrder")
	public String NewOrder(Model model, HttpSession session) {
		currentDate = new Date(System.currentTimeMillis());
		personList = new ArrayList<Person>();
		subType = new ArrayList<Product>();
		session.setAttribute("orderID", getRefNo());
		session.setAttribute("lotNo", getLotNo());
		session.setAttribute("currentDate", function.getCurrentDate());

		session.setAttribute("orderDetails", new OrderDetail(true));
		// Date currentDate = new Date(System.currentTimeMillis());

		model.addAttribute("orderDetail", new OrderDetail());
		model.addAttribute("productList", getProductList(1));
		model.addAttribute("personList", getPerson());

		return "NewOrder"; // Returns page named mentioned

	}

	@PostMapping(value = "Order/save")
	private @ResponseBody String save(@RequestBody OrderDetailWrapperClass data, Errors errors) {
		orderDetailList = new ArrayList<>();
		if (errors.hasErrors()) {
			System.out.println(errors.toString());
		}

		Date orderDate = currentDate;
		Person person = selectedPerson(Integer.parseInt(data.getPartyName()));
		savedOrder = new Order();
		savedOrder.setLotNo(getLotNo());
		savedOrder.setDate(orderDate);
		savedOrder.setOrderStatus(Constants.open);
		savedOrder.setPerson(person);
		savedOrder.setRefNo(getRefNo());

		int i = 0;
		for (OrderDetail wrapper : data.getOrderDetail()) {

			String selectedProduct = data.getProduct().get(i);
			String[] machine = data.getMachineDetail().get(i).split(",");
			for (Product product : getSubType(selectedProduct)) {
				double quantity = 0;
				quantity = wrapper.getQuantity() / prodType;
				ProductDetail pD = new ProductDetail(wrapper.getProdDetail().getDesignNo(),
						wrapper.getProdDetail().getColor(), product);
				OrderDetail OD = new OrderDetail(wrapper.getConstruction(), pD, quantity, wrapper.getRemarks(),
						wrapper.getNoOfColors());

				switch (pD.getProduct().getName()) {
				case Constants.shalwar: {
					String[] itemDetail = machine[0].split("~");
					OD.setMachineID(getSelectedOperation(itemDetail[0]));
					OD.setExpectedEndDate(functions.getSQLDate(itemDetail[2]));

					break;
				}
				case Constants.kameez: {
					String[] itemDetail = machine[1].split("~");
					OD.setMachineID(getSelectedOperation(itemDetail[0]));
					OD.setExpectedEndDate(functions.getSQLDate(itemDetail[2]));
					break;
				}
				case Constants.duppatta: {
					String[] itemDetail = machine[2].split("~");
					OD.setMachineID(getSelectedOperation(itemDetail[0]));
					OD.setExpectedEndDate(functions.getSQLDate(itemDetail[2]));
					break;
				}
				}

				OD.getProdDetail().setProduct(product);

				OD.setOrder(savedOrder);
				save(OD);

			}
			i++;
		}

		return "orderDetails";

	}

	private void save(OrderDetail OD) {
		orderDetailService.save(OD);
		orderDetailList.add(OD);
	}

	private void init() {
		operationsList = new ArrayList<DeptOperationDetails>();
		OIPList = new ArrayList<OperationInProgress>();

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
	public @ResponseBody List<String> hello(@Valid @RequestBody String product) {

		List<String> result = new ArrayList<>();
		for (int i = 0; i < productlist.size(); i++) {

			if (productlist.get(i).getProd_ID().toString().equals(product)) {
				result.add(productlist.get(i).getProdType());
			}

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
			if (deptOD != null && deptOD.getColor() >= Integer.parseInt(color)) {
				String formattedValue = formatter.format(deptOD.getColor());
				result.add(formattedValue + " Colors " + "|" + " " + deptOD.getName());
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
						System.err.println("ORder Number  : " + result[1] + " ---- Order Date " + result[2]);

						// return resultList;
					}
				} else {
					result[3] = Constants.open;
					result[2] = computeOrderEndDate(deptOD, input[1], currentDate);
					resultList.add(result);
					System.err.println("Is Open " + " ---- Order Date " + result[2]);
				}
				resultList.addAll(getScheduledOrder(deptOD));
				if (LastScheduledOrder != null)
					result[2] = computeOrderEndDate(deptOD, input[1], LastScheduledOrder);

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

			String quantity = OD.getQuantity() + "";
			// result[2] = computeOrderEndDate(deptOD, quantity, OD.getExpectedEndDate());
			result[2] = OD.getExpectedEndDate().toString();
			resultList.add(result);
			System.err.println("ORder Number  : " + result[1] + "---- Order Date " + result[2]);
			LastScheduledOrder = OD.getExpectedEndDate();
		}
		return resultList;
	}

	// @PostMapping(value = "getSubType")
	public List<Product> getSubType(String prod) {
		// Product product = getSelectedProduct(prod);
		// List<String> result = new ArrayList<>();
		//
		// ProdService.findByParentRef(product.getProd_ID());
		// for (Product p : subType)
		// result.add(p.getProd_ID() + "," + p.getName());
		// return result;

		Product product = getSelectedProduct(prod);
		prodType = Integer.parseInt(product.getProdType());
		List<Product> result = new ArrayList<Product>();
		return ProdService.findByParentRef(product.getProd_ID());

	}

	public String computeOrderEndDate(DeptOperationDetails deptOD, String orderQuantity, Date StartDate) {
		double baseProduction = deptOD.getBaseProduction();
		double quantity = Double.parseDouble(orderQuantity);
		int days = (int) Math.ceil(quantity / baseProduction);
		return days + "/" + function.addDays(days, StartDate);

	}

	@GetMapping("/ViewOrder")
	public String getAll(Model model) {
		List<Order> orderList = null;
		orderList = orderService.getAll();
		model.addAttribute("orderList", orderList);
		return "ViewAllOrder";
	}

	public Product getSelectedProduct(String product) {
		String[] prod = product.split("/");
		prodType = 0;

		for (int i = 0; i < productlist.size(); i++) {

			if (productlist.get(i).getName().equals(prod[0]) && productlist.get(i).getProdType().equals(prod[1])) {
				return productlist.get(i);
			}
		}

		return null; // not found
	}

	public Person selectedPerson(int personID) {
		for (Person p : personList) {
			if (p.getPersonID() == personID)
				return p;
		}
		return null;
	}

	public Integer getSelectedOperation(String operationName) {
		for (DeptOperationDetails deptOD : operationsList) {
			if (deptOD.getName().equals(operationName))
				return deptOD.getDeptOD_ID();

		}
		return null;

	}

	public List<Person> getPerson() {

		personList = personService.getAll();
		return personList;

	}

	protected List<Product> getProductList(int isFinal) {
		productlist = ProdService.findFinalProduct(isFinal);
		return productlist;
	}

	// order related
	public String getLotNo() {

		String lotNo = "LN-01" + String.format("%05d", (getOrderID() + 1));
		// will be changed to XML parameter
		return lotNo;
	}

	public String getRefNo() {
		String refNo = "MO-" + String.format("%05d", (getOrderID() + 1)); // 5 digit formated
		// will be changed to XML parameter

		return refNo;
	}

	int orderID;

	private int getOrderID() {
		orderID = orderService.lastPKValue();
		return orderID;
	}

	// OIP related
	public void computeEstimateDate() {
		// OIPList.clear();
		// OIPList.

	}

}

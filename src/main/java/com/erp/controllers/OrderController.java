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
	@Autowired
	private ProductDetailService prodDetailService;

	List<Product> productlist;
	List<Person> personList;
	List<OrderDetail> orderDetailList;
	Product selectedProduct;
	Order savedOrder;
	DecimalFormat formatter = new DecimalFormat("00");
	Constants constant;

	// ----------------------
	@Autowired
	private DepartmentService DeptService;
	@Autowired
	private DeptOperationService operationService;
	@Autowired
	private OperationInProgessService OIPservice;

	List<DeptOperationDetails> operationsList;
	List<OperationInProgress> OIPList;
	
	
	

	@GetMapping(value = "/NewOrder")
	public String NewOrder(Model model, HttpSession session) {

		personList = new ArrayList<Person>();

		session.setAttribute("orderID", getRefNo());
		session.setAttribute("lotNo", getLotNo());
		session.setAttribute("orderDetails", new OrderDetail(true));

		model.addAttribute("orderDetail", new OrderDetail());
		model.addAttribute("productList", getProductList());
		model.addAttribute("personList", getPerson());

		return "NewOrder"; // Returns page named mentioned

	}

	@PostMapping(value = "Order/save")
	private @ResponseBody String save(@RequestBody OrderDetailWrapperClass data, Errors errors) {
		orderDetailList = new ArrayList<>();
		if (errors.hasErrors()) {
			System.out.println(errors.toString());
		}

		Product product = new Product();
		Date orderDate = new Date(System.currentTimeMillis());
		Person person = selectedPerson(Integer.parseInt(data.getPartyName()));
		savedOrder = new Order();
		savedOrder.setLotNo(getLotNo());
		savedOrder.setDate(orderDate);
		savedOrder.setOrderStatus(constant.open);
		savedOrder.setPerson(person);
		savedOrder.setRefNo(getRefNo());

		for (int i = 0; i < data.getOrderDetail().size(); i++) {
			String selectedProduct = data.getProduct().get(i);
			product = getSelectedProduct(selectedProduct);

			data.getOrderDetail().get(i).getProdDetail().setProduct(product);

			data.getOrderDetail().get(i).setOrder(savedOrder);

			orderDetailService.save(data.getOrderDetail().get(i));
			orderDetailList.add(data.getOrderDetail().get(i));
		}

		return "orderDetails";

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
				System.out.println(productlist.get(i).getProdType());
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
	public @ResponseBody String[] getEndDate(@RequestBody String machineName) {
		String[] result = new String[3];
		for (DeptOperationDetails deptOD : operationsList) {
			if (deptOD.getName().equalsIgnoreCase(machineName)) {
				
				OIPList = OIPservice.findByDeptODAndStatus(deptOD,constant.inProgress);
				for(OperationInProgress OIP : OIPList){
					result[0] = OIP.getExpectedEndDate().toString();
					result[1] = OIP.getOrderDetail().getOrder().getRefNo();
					return result;
				}
				
			}
		}

		return null;

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

	public List<Person> getPerson() {

		personList = personService.getAll();
		return personList;

	}

	protected List<Product> getProductList() {
		productlist = ProdService.findAll();
		return productlist;
	}

	//order related
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
//OIP related
	public void computeEstimateDate() {
//	OIPList.clear();
//	OIPList.
		
		
	}
	
	
	
	
	
	
	
}

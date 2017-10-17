package com.erp.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
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

import com.erp.classes.DeptOperationDetails;
import com.erp.classes.OperationInProgress;
import com.erp.classes.Order;
import com.erp.classes.OrderDetail;
import com.erp.classes.OrderDetailWrapperClass;
import com.erp.classes.Person;
import com.erp.classes.Product;
import com.erp.classes.ProductDetail;
import com.erp.services.DepartmentService;
import com.erp.services.OperationInProgessService;
import com.erp.services.OrderDetailService;
import com.erp.services.OrderService;
import com.erp.services.PersonDetialService;
import com.erp.services.PersonService;
import com.erp.services.ProductDetailService;
import com.erp.services.ProductService;

@Controller
@SessionAttributes(value = "productDetail", types = { ProductDetail.class })
public class ConfirmOrderController {

	@Autowired
	private DepartmentService DeptService;
	@Autowired private DeptOperationController operationService;
	@Autowired
	private OperationInProgessService OIPservice;

	List<Product> productlist;
	List<Person> personName;
	List<OrderDetail> orderDetailList;
	Product selectedProduct;
	List<DeptOperationDetails> operationsList;
	List<OperationInProgress> OIPList;

	@GetMapping("ConfirmOrder")
	public String operationList(Model model) {
		operationsList = new ArrayList<DeptOperationDetails>();
		OIPList = new ArrayList<OperationInProgress>();
		operationsList = operationService.getAll();
		model.addAttribute("operationList", operationsList);
		model.addAttribute("dept", DeptService.getAll());
		// OIPList = OIPservice.getAll();
		// OIPservice.findByOrderDetail(orderdetail);
		return "ConfirmOrder";
	}

	//
	// @GetMapping(value = "/NewOrder")
	// public String NewOrder(Model model, HttpSession session) {
	//
	// personName = new ArrayList<Person>();
	//
	// session.setAttribute("orderID", getOrderNo());
	// session.setAttribute("lotNo", getLotNo());
	// session.setAttribute("orderDetails", new OrderDetail(true));
	//
	// model.addAttribute("orderDetail", new OrderDetail());
	// model.addAttribute("productList", getProductList());
	// model.addAttribute("personList", getPerson());
	//
	// return "NewOrder"; // Returns page named mentioned
	//
	// }
	//
	// @PostMapping(value = "Order/save")
	// private @ResponseBody String save(@RequestBody OrderDetailWrapperClass data,
	// Errors errors) {
	//
	// if (errors.hasErrors()) {
	// System.out.println(errors.toString());
	// }
	//
	// Product product = new Product();
	// Date orderDate = new Date(System.currentTimeMillis());
	// Person person = selectedPerson(Integer.parseInt(data.getPartyName()));
	// Order order = new Order();
	// order.setLotNo(getLotNo());
	// order.setDate(orderDate);
	// order.setOrderStatus("open");
	// order.setPerson(person);
	//
	// for (int i = 0; i < data.getOrderDetail().size(); i++) {
	// String selectedProduct = data.getProduct().get(i);
	// product = getSelectedProduct(selectedProduct);
	//
	// data.getOrderDetail().get(i).getprodDetail().setProduct(product);
	//
	// data.getOrderDetail().get(i).setOrder(order);
	//
	// // orderDetailService.save(data.getOrderDetail().get(i));
	//
	// }
	//
	// return "ViewOrder";
	//
	// }
	//
	// @PostMapping(value = "getProdType")
	// public @ResponseBody List<String> hello(@Valid @RequestBody String search) {
	//
	// List<String> result = new ArrayList<>();
	// String[] product = search.split("=");
	// for (int i = 0; i < productlist.size(); i++) {
	//
	// if (productlist.get(i).getProd_ID().toString().equals(product[0])) {
	// System.out.println(search);
	// System.out.println(productlist.get(i).getProdType());
	// result.add(productlist.get(i).getProdType());
	// }
	//
	// }
	// return result;
	// }
	//
	// @GetMapping("/ViewOrder")
	// public String getAll(Model model) {
	// List<Order> orderList = null;
	// orderList = orderService.getAll();
	// model.addAttribute("orderList", orderList);
	// return "ViewAllOrder";
	// }
	//
	// public List<Person> getPerson() {
	//
	// if (!(personName == null))
	// personName.clear();
	//
	// personName = personService.getAll();
	// return personName;
	// // int i = 0;
	// //
	// // for (Person person : personService.getAll()) {
	// //
	// // personName.add(i, person.getFname() + " " + person.getLname());
	// // i++;
	// //
	// // }
	//
	// }
	//
	// public Product getSelectedProduct(String product) {
	// String[] prod = product.split("/");
	//
	// for (int i = 0; i < productlist.size(); i++) {
	//
	// if (productlist.get(i).getName().equals(prod[0]) &&
	// productlist.get(i).getProdType().equals(prod[1])) {
	//
	// return productlist.get(i);
	// }
	// }
	//
	// return null; // not found
	// }
	//
	// public Person selectedPerson(int personID) {
	// for (Person p : personName) {
	// if (p.getPersonID() == personID)
	// return p;
	// }
	// return null;
	// }
	//
	// public String getLotNo() {
	//
	// String lotNo = "LN-01" + String.format("%05d", (getOrderID() + 1));
	// // will be changed to XML parameter
	// return lotNo;
	// }
	//
	// public String getOrderNo() {
	// String nextOrderID = "MO-" + String.format("%05d", (getOrderID() + 1)); // 5
	// digit formated
	// // will be changed to XML parameter
	//
	// return nextOrderID;
	// }
	//
	// private int getOrderID() {
	// return orderService.lastPKValue();
	// }
	//
	// protected List<Product> getProductList() {
	// if (!(productlist == null))
	// productlist.clear();
	// productlist = ProdService.findAll();
	// ;
	// return productlist;
	// }

}

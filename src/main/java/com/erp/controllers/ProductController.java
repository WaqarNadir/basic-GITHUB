package com.erp.controllers;

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

import com.erp.classes.Constants;
import com.erp.classes.Product;
import com.erp.classes.ProductDetail;
import com.erp.services.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping(value = "/NewProduct")
	public String NewProduct(Model model) {
		model.addAttribute("product", new Product());
		return "NewProduct";
	}

	@PostMapping("/product")
	public String saveProduct(@ModelAttribute Product product) {
		product.setIsFinal(1);
		save(product);
		saveChild(product.getProd_ID(), product.getProdType());
		return "NewProduct";
	}

	@RequestMapping("/product")
	public List<Product> getProduct() {

		return service.findAll();
	}

	public void saveChild(int id, String prodType) {
		Product shalwar = new Product(Constants.shalwar, id);
		Product kameez = new Product(Constants.kameez, id);
		
		if (prodType.equals("3")) {
			Product duppatta = new Product(Constants.duppatta, id);
			save(duppatta);
		}
		save(kameez);
		save(shalwar);

	}

	public void save(Product product) {
		service.save(product);

	}

}

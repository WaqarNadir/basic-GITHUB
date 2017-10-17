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

import com.erp.classes.Product;
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
	    public String AddProduct(@ModelAttribute Product product) {
		 
	       System.out.println(product.toString());
	       save(product);
	       return "NewProduct";
	    }
	
	 @RequestMapping("/product")
		public List<Product> getProduct() {
				
			return service.findAll();
		}
	//@RequestMapping(method=RequestMethod.POST,value="/Product")
	 
	public void save(Product product) {
		service.save(product);
		
	}
	@RequestMapping(method=RequestMethod.PUT,value="/UpdateProduct/{id}")
	public void update(@RequestBody Product Product,@PathVariable int id) {
		service.update(id, Product);
		
	}
	@RequestMapping(method=RequestMethod.DELETE,value="/Product/{id}")
	public void delete(@PathVariable int id) {
		service.delete(id);
		
	}
	@RequestMapping(method=RequestMethod.GET,value="/Product/{id}")
	public Product GetProduct(@PathVariable int id) {
		return service.find(id);
		
	}

	
}

package com.erp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.erp.classes.ProductDetail;
import com.erp.services.ProductDetailService;

@Controller
public class ProductDetailController {
	
	@Autowired
	private ProductDetailService service;
	
	 @PostMapping("/productDetial")
	    public String AddProduct(@ModelAttribute ProductDetail ProductDetail) {
		 
	       System.out.println(ProductDetail.toString());
	       save(ProductDetail);
	       return "productDetail";
	    }
	
	
	@RequestMapping("/ProductDetail")
	public List<ProductDetail> getAll(){
		return service.getAll();
	}
	
	//@RequestMapping(method=RequestMethod.POST,value="/NewProductDetial")
	public void save( ProductDetail  ProductDetail ) {
		service.save(ProductDetail);
		
	}
	@RequestMapping(method=RequestMethod.PUT,value="/UpdateProductDetail /{id}")
	public void update(@RequestBody ProductDetail ProductDetail ,@PathVariable int id) {
		service.update(id, ProductDetail );
		
	}
	@RequestMapping(method=RequestMethod.DELETE,value="/ProductDetail /{id}")
	public void delete(@PathVariable int id) {
		service.delete(id);
		
	}
	@RequestMapping(method=RequestMethod.GET,value="/ProductDetail /{id}")
	public ProductDetail GetProductDetail (@PathVariable int id) {
		return service.find(id);
		
	}

}

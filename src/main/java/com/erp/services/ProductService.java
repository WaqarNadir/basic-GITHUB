package com.erp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.ls.LSInput;

import com.erp.classes.Product;
import com.erp.repo.ProductRepo;

@Component
public class ProductService {

	@Autowired
	ProductRepo repo;

	List<Product> productlist = new ArrayList<Product>();

	public Product find(int id) {
		// TODO Auto-generated method stub
		return repo.findOne(id);
	}

	public List<Product> findAll() {
		productlist.clear();
		repo.findAll().forEach(productlist::add);
		return productlist;
	}

	public void save(Product product) {
		// TODO Auto-generated method stub
		repo.save(product);

	}

	public void update(int id, Product product) {
		// TODO Auto-generated method stub
		repo.save(product);

	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		repo.delete(id);

	}

}

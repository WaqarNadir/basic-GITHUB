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
		return repo.findAll();

	}

	public List<Product> findFinalProduct(int isFinal) {
		return repo.findByIsFinal(isFinal);
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

	public int nextID() {
		return 0;
	}

	public List<Product> findByParentRef(int prodID) {
		return repo.findByParentRef(prodID);

	}

}

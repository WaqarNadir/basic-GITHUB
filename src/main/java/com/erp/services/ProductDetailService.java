package com.erp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.erp.classes.ProductDetail;
import com.erp.repo.ProductDetailRepo;

@Component
@Service
public class ProductDetailService {

	@Autowired
	private ProductDetailRepo repo;
	List<ProductDetail> PD = new ArrayList<ProductDetail>();

	public void save(ProductDetail PD) {
		repo.save(PD);

	}

	public void delete(int id) {
		repo.delete(id);

	}

	public void update(int id, ProductDetail PD) {
		repo.save(PD);

	}

	public ProductDetail find(int id) {
		return repo.findOne(id);

	}

	public List<ProductDetail> getAll() {
		repo.findAll().forEach(PD::add);
		return PD;

	}

}

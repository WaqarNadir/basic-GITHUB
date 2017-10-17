package com.erp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.ProductDetail;

@Repository
public interface ProductDetailRepo extends CrudRepository<ProductDetail, Integer> {
}
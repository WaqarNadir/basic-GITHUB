package com.erp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer> {

}
package com.erp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.Product;
import java.lang.Integer;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
	List<Product> findByIsFinal(Integer isFinal);
	List<Product> findByParentRef(int prodID);

}
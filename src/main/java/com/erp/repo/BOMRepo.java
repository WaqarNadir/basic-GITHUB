package com.erp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.BillOfMaterial;

@Repository
public interface BOMRepo extends CrudRepository<BillOfMaterial, Integer> {
}
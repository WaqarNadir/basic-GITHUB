package com.erp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.classes.BillOfMaterial;
import com.erp.repo.BOMRepo;

@Service
public class BOMService {
	@Autowired
	public BOMRepo repo;

	List<BillOfMaterial> billOfMaterialList = new ArrayList<BillOfMaterial>();

	public void save(BillOfMaterial BillOfMaterial) {
		repo.save(BillOfMaterial);

	}

	public void delete(int id) {
		repo.delete(id);

	}

	public void update(int id, BillOfMaterial BillOfMaterial) {
		repo.save(BillOfMaterial);

	}

	public BillOfMaterial find(int id) {
		System.out.println(repo.findOne(id));
		return repo.findOne(id);

	}

	public List<BillOfMaterial> getAll() {
		billOfMaterialList.clear();
		repo.findAll().forEach(billOfMaterialList::add);
		return billOfMaterialList;

	}

}

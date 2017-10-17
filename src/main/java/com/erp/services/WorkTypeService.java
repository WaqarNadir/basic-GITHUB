package com.erp.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.boot.registry.classloading.spi.ClassLoaderService.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.classes.WorkType;
import com.erp.repo.WorkTypeRepo;

@Service
public class WorkTypeService {
	@Autowired
	public WorkTypeRepo repo;

	List<WorkType> WorkType = new ArrayList<WorkType>();

	public void save(WorkType WorkType) {
		repo.save(WorkType);

	}

	public void delete(int id) {
		repo.delete(id);

	}

	public void update(int id, WorkType WorkType) {
		repo.save(WorkType);

	}

	public WorkType find(int id) {

		return repo.findOne(id);

	}

	public List<WorkType> getAll() {
		WorkType.clear();
		repo.findAll().forEach(WorkType::add);
		return WorkType;

	}

}

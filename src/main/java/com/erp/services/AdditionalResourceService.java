package com.erp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.classes.AdditionalResource;
import com.erp.repo.AdditionalResourceRepo;

@Service
public class AdditionalResourceService {
	@Autowired
	public AdditionalResourceRepo repo;

	List<AdditionalResource> additionalResourceList = new ArrayList<AdditionalResource>();

	public void save(AdditionalResource AdditionalResource) {
		repo.save(AdditionalResource);

	}

	public void delete(int id) {
		repo.delete(id);

	}

	public void update(int id, AdditionalResource AdditionalResource) {
		repo.save(AdditionalResource);

	}

	public AdditionalResource find(int id) {
		System.out.println(repo.findOne(id));
		return repo.findOne(id);

	}

	public List<AdditionalResource> getAll() {
		additionalResourceList.clear();
		repo.findAll().forEach(additionalResourceList::add);
		return additionalResourceList;

	}

}

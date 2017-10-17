package com.erp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.classes.LossReason;
import com.erp.repo.LossReasonRepo;

@Service
public class LossReasonService {
	@Autowired
	private LossReasonRepo repo;

	List<LossReason> lossReasonList = new ArrayList<LossReason>();

	
	public void save(LossReason LossReason) {
		repo.save(LossReason);

	}

	public void delete(int id) {
		repo.delete(id);

	}

	public void update(int id, LossReason LossReason) {
		repo.save(LossReason);

	}

	public LossReason find(int id) {
		System.out.println(repo.findOne(id));
		return repo.findOne(id);

	}

	public List<LossReason> getAll() {
		lossReasonList.clear();
		repo.findAll().forEach(lossReasonList::add);
		return lossReasonList;
	}
}

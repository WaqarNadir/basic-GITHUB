package com.erp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.erp.classes.DailyProduction;
import com.erp.classes.OperationInProgress;
import com.erp.repo.DailyProductionRepo;

@Service
public class DailyProductionService {
	@Autowired
	public DailyProductionRepo repo;

	List<DailyProduction> dailyProductionList = new ArrayList<DailyProduction>();

	public void save(DailyProduction DailyProduction) {
		repo.save(DailyProduction);

	}

	public void delete(int id) {
		repo.delete(id);

	}

	public void update(int id, DailyProduction DailyProduction) {
		repo.save(DailyProduction);

	}

	public DailyProduction find(int id) {
		System.out.println(repo.findOne(id));
		return repo.findOne(id);

	}

	public List<DailyProduction> getAll() {
		dailyProductionList.clear();
		repo.findAll().forEach(dailyProductionList::add);
		return dailyProductionList;

	}

	public List<DailyProduction> findByOIP(OperationInProgress oip) {

		return repo.findByOIP(oip);
	}

	public double getClosingStock(OperationInProgress oip) {
		dailyProductionList.clear();
		double closingStock = 0;
		dailyProductionList = repo.LatestClosingStock(oip, new PageRequest(0, 1));
		if (!dailyProductionList.isEmpty())
			closingStock = dailyProductionList.get(0).getClosingStock();

		return closingStock;
	}

}

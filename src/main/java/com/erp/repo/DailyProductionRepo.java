package com.erp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.DailyProduction;

@Repository
public interface DailyProductionRepo extends CrudRepository<DailyProduction, Integer> {
}
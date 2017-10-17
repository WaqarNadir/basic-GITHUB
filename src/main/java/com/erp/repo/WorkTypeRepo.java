package com.erp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.WorkType;

@Repository
public interface WorkTypeRepo extends CrudRepository<WorkType, Integer> {
}
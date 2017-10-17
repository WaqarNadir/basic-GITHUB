package com.erp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.LossReason;

@Repository
public interface LossReasonRepo extends CrudRepository<LossReason, Integer> {

}
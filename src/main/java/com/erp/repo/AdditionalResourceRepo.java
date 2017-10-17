package com.erp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.AdditionalResource;

@Repository
public interface AdditionalResourceRepo extends CrudRepository<AdditionalResource, Integer> {
}
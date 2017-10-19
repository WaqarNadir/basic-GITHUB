package com.erp.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erp.classes.DailyProduction;
import com.erp.classes.OperationInProgress;



import java.util.List;

@Repository
public interface DailyProductionRepo extends CrudRepository<DailyProduction, Integer> {

	List<DailyProduction> findByOIP(OperationInProgress oip);
	
	List<DailyProduction> LatestClosingStock(@Param("oip") OperationInProgress oip,Pageable pageable);

}

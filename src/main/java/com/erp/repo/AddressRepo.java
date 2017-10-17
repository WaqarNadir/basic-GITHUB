package com.erp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.Address;
import com.erp.classes.Person;
import java.util.List;

@Repository
public interface AddressRepo extends CrudRepository<Address, Integer> {
	//List<Address> findByPerson(Person person);
}
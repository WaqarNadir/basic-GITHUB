package com.erp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.Person;
import com.erp.classes.Phone;
import java.lang.String;
import java.util.List;

@Repository
public interface PhoneRepo extends CrudRepository<Phone, Integer> {
//	List<Phone> findByPerson(Person person);
}
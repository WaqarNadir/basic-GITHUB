package com.erp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erp.classes.Email;
import com.erp.classes.Person;
import java.util.List;

@Repository
public interface EmailRepo extends CrudRepository<Email, Integer> {
	//List<Email> findByPerson(Person person);
}
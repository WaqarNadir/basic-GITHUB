package com.erp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.erp.classes.WorkType;
import com.erp.services.WorkTypeService;

@RestController
public class WorktypeController {
	
	@Autowired
	private WorkTypeService service;
	
	@RequestMapping("worktype")
	public List<WorkType> getAll(){
		return service.getAll();
	}


	@RequestMapping(method=RequestMethod.POST,value="/NewWorkType")
	public void save(@RequestBody WorkType WorkType ) {
		service.save( WorkType);
		
	}
	@RequestMapping(method=RequestMethod.PUT,value="/UpdateWorkType/{id}")
	public void update(@RequestBody  WorkType WorkType,@PathVariable int id) {
		service.update(id,  WorkType);
		
	}
	@RequestMapping(method=RequestMethod.DELETE,value="/ WorkType /{id}")
	public void delete(@PathVariable int id) {
		service.delete(id);
		
	}
	@RequestMapping(method=RequestMethod.GET,value="/ WorkType /{id}")
	public  WorkType GetWorkType(@PathVariable int id) {
		return service.find(id);
		
	}


}

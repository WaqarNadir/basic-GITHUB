package com.erp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.erp.classes.AdditionalResource;
import com.erp.services.AdditionalResourceService;

@RestController
public class AdditionalResourceController {
	
	@Autowired
	private AdditionalResourceService service;
	
	@RequestMapping("AR")
	public List<AdditionalResource> getAll(){
		return service.getAll();
	}

	@RequestMapping(method=RequestMethod.POST,value="/NewAR")
	public void save(@RequestBody AdditionalResource AdditionalResource) {
		service.save( AdditionalResource);
		
	}
	@RequestMapping(method=RequestMethod.PUT,value="/UpdateAR/{id}")
	public void update(@RequestBody AdditionalResource AdditionalResource,@PathVariable int id) {
		service.update(id,  AdditionalResource);
		
	}
	@RequestMapping(method=RequestMethod.DELETE,value="/AR/{id}")
	public void delete(@PathVariable int id) {
		service.delete(id);
		
	}
	@RequestMapping(method=RequestMethod.GET,value="/AR/{id}")
	public  AdditionalResource GetAdditionalResource(@PathVariable int id) {
		return service.find(id);
		
	}


}

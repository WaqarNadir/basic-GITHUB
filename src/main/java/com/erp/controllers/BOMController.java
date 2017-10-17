package com.erp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.erp.classes.BillOfMaterial;
import com.erp.services.BOMService;

@RestController
public class BOMController {
	
	@Autowired
	private BOMService service;
	
	@RequestMapping("BOM")
	public List<BillOfMaterial> getAll(){
		return service.getAll();
	}

	@RequestMapping(method=RequestMethod.POST,value="/NewBOM")
	public void save(@RequestBody BillOfMaterial BillOfMaterial) {
		service.save( BillOfMaterial);
		
	}
	@RequestMapping(method=RequestMethod.PUT,value="/UpdateBOM/{id}")
	public void update(@RequestBody BillOfMaterial BillOfMaterial,@PathVariable int id) {
		service.update(id,  BillOfMaterial);
		
	}
	@RequestMapping(method=RequestMethod.DELETE,value="/BOM/{id}")
	public void delete(@PathVariable int id) {
		service.delete(id);
		
	}
	@RequestMapping(method=RequestMethod.GET,value="/BOM/{id}")
	public  BillOfMaterial GetBillOfMaterial(@PathVariable int id) {
		return service.find(id);
		
	}


}

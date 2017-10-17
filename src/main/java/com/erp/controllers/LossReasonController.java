package com.erp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.erp.classes.LossReason;
import com.erp.services.LossReasonService;

@RestController
public class LossReasonController {
	
	@Autowired
	private LossReasonService service;
	
	@RequestMapping("LossReason")
	public List<LossReason> getAll(){
		return service.getAll();
	}

	@RequestMapping(method=RequestMethod.POST,value="/NewLossReason")
	public void save(@RequestBody LossReason  LossReason) {
		service.save(LossReason );
		
	}
	@RequestMapping(method=RequestMethod.PUT,value="/UpdateLossReason/{id}")
	public void update(@RequestBody LossReason LossReason,@PathVariable int id) {
		service.update(id, LossReason );
		
	}
	@RequestMapping(method=RequestMethod.DELETE,value="/LossReason/{id}")
	public void delete(@PathVariable int id) {
		service.delete(id);
		
	}
	@RequestMapping(method=RequestMethod.GET,value="/LossReason /{id}")
	public LossReason GetLossReason(@PathVariable int id) {
		return service.find(id);
		
	}



}

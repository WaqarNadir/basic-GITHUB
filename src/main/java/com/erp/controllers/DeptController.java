package com.erp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.erp.classes.Department;
import com.erp.services.DepartmentService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class DeptController {

	@Autowired
	private DepartmentService service;

	List<Department> DepartList = null;

	@GetMapping(value = "NewDept")
	public String home(Model model) {

		model.addAttribute("dept", new Department());
		return "NewDept";
	}

	@PostMapping(value = "Dept/save")
	public String saveDept(@ModelAttribute Department dept, Model model) {
		save(dept);
		model.addAttribute("DepartList", getAll());
		return "ViewAllDept";
	}

	@RequestMapping("Dept/All")
	public String ViewAll(Model model) {
		model.addAttribute("deptList", getAll());
		return "ViewAllDept";
	}

	public void save(Department Department) {
		service.save(Department);
	}

	public List<Department> getAll() {

		DepartList = service.getAll();
		return DepartList;
	}

	// @RequestMapping(method=RequestMethod.PUT,value="/UpdateDept/{id}")
	public void update(@RequestBody Department Department, @PathVariable int id) {
		service.update(id, Department);

	}

	// @RequestMapping(method=RequestMethod.DELETE,value="/Dept/{id}")
	public void delete(@PathVariable int id) {
		service.delete(id);

	}

	// @RequestMapping(method=RequestMethod.GET,value="/Dept/{id}")
	public Department GetDept(@PathVariable int id) {
		return service.find(id);

	}
}

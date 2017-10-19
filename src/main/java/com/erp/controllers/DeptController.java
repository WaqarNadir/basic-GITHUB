package com.erp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erp.classes.Department;
import com.erp.services.DepartmentService;
import com.erp.services.DeptOperationService;

@Controller
public class DeptController {

	@Autowired
	private DepartmentService service;
	@Autowired
	private DeptOperationService deptODService;

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

	

}

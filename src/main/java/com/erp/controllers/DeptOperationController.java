package com.erp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.classes.Constants;
import com.erp.classes.Department;
import com.erp.classes.DeptOperationDetails;
import com.erp.services.DepartmentService;
import com.erp.services.DeptOperationService;

@Controller
public class DeptOperationController {

	@Autowired
	private DeptOperationService service;
	@Autowired
	private DepartmentService Deptservice;

	List<Department> DeptList = null;
	List<DeptOperationDetails> DeptOPList = null;
	Constants constant;

	@RequestMapping("/DeptOP")
	public String home(Model model) {
		String name = null;
		model.addAttribute("deptOP", new DeptOperationDetails());
		model.addAttribute("deptList", getDept());
		model.addAttribute("SelectedDept", new String());
		return "NewDeptOperation";

	}

	@RequestMapping("/DeptOP/save")
	public String save(@ModelAttribute DeptOperationDetails DeptOP, @RequestBody String SelectedDept, Model model) {
		Department dept = new Department();

		dept = Deptservice.getAll().get(DeptOP.getDept().getDept_ID());

		DeptOP.setDept(dept);
		service.save(DeptOP);
		dept.setEstimatedCompletionTime(DeptEstimatePerUnit(dept));
		System.err.println(DeptEstimatePerUnit(dept)*10000);
		Deptservice.save(dept);

		model.addAttribute("deptOPList", service.getAll());
		return "ViewAllOperations";

	}

	@RequestMapping("DeptOP/All")
	public String ViewAll(Model model) {
		model.addAttribute("deptOPList", getAll());
		return "ViewAllOperations";
	}

	public List<Department> getDept() {
		DeptList = new ArrayList<Department>();
		DeptList = Deptservice.getAll();
		return DeptList;

	}

	public List<DeptOperationDetails> getAll() {
		if (DeptOPList == null)
			DeptOPList = service.getAll();
		return DeptOPList;
	}

	public double DeptEstimatePerUnit(Department dept) {
		List<DeptOperationDetails> deptOD = new ArrayList<DeptOperationDetails>();
		List<Double> dayPerOperation = new ArrayList<>();
		double totalDays = 0;
		if (!dept.getName().equals("Printing")) {
			// printing dept not follow any sequence
			deptOD = service.findByDept(dept);

			deptOD = service.findByDept(dept);
			for (DeptOperationDetails operation : deptOD) {
				double dayPerUnit = constant.deptUnit / operation.getBaseProduction();
				dayPerOperation.add(dayPerUnit);
				totalDays += dayPerUnit;
			}
		} // if
System.err.println("in function estimate date");
		return totalDays;

		// value checker
		// for(Department odept : getAll()) {
		// System.out.println(odept.getName()+" : "+DeptEstimatePerUnit(odept)*10000);
		// }

	}
}

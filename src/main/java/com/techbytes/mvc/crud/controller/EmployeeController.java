package com.techbytes.mvc.crud.controller;


import com.techbytes.mvc.crud.model.Employee;
import com.techbytes.mvc.crud.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping
    public String welcomePage(){
        return "index";
    }

    @GetMapping("/showForm")
    public String showForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @GetMapping("/employee-details")
    public String getEmployees(Model model){
        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);
        return "employee-detail";
    }

    @PostMapping("/processForm")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "employee-form";
        }
        employeeService.saveEmployee(employee);
        System.out.println("Employee saved in db= "+employee);
        Map<String, Object> model = bindingResult.getModel();
        return "redirect:/employee-details";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("employeeId") int employeeId, Model model){
        Employee employee = employeeService.findById(employeeId);
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("employeeId") int employeeId){
        employeeService.deleteById(employeeId);
        return "redirect:/employee-details";
    }
}

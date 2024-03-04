package com.techbytes.mvc.crud.service;

import com.techbytes.mvc.crud.model.Employee;
import com.techbytes.mvc.crud.repository.EmployeeDao;
import com.techbytes.mvc.crud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {


    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getEmployees() {
//        return employeeDao.getEmployees();
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee findById(int employeeId) {
        return employeeRepository.findById(employeeId).orElse(new Employee());
    }

    public void deleteById(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}

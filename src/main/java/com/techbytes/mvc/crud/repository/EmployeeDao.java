package com.techbytes.mvc.crud.repository;

import com.techbytes.mvc.crud.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDao {

    private EntityManager entityManager;

    public EmployeeDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Employee> getEmployees() {
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);
        List<Employee> resultList = query.getResultList();
        return resultList;
    }

    @Transactional
    public void saveEmployee(Employee employee) {
        entityManager.persist(employee);
    }
}

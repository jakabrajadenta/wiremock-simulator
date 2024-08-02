package com.braja.service;

import com.braja.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> findAll() {
        return this.employeeList;
    }

    public Employee findOne(int id) {
        return this.employeeList.stream().filter(employee -> employee.getId() == id).findFirst().orElse(null);
    }

    public void save(Employee employee) {
        this.employeeList.add(employee);
    }

    public void delete(int id) {
        this.employeeList.removeIf(employee -> employee.getId() == id);
    }

}

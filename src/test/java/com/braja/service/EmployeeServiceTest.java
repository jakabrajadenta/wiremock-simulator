package com.braja.service;

import com.braja.model.Employee;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EmployeeServiceTest {

    private EmployeeService employeeService;

    @BeforeClass
    public static void oneTimeSetUp() {
        // one-time initialization code
        System.out.println("Setting up one time");
    }

    @AfterClass
    public static void oneTimeTearDown() {
        // one-time cleanup code
        System.out.println("Tearing down one time");
    }

    @Before
    public void setUp() {
        System.out.println("Setting up");
        employeeService = new EmployeeService();
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down");
        employeeService = null;
    }

    @Test
    public void testFindAll() {
        Employee employee1 = new Employee(1, "Bobo", "IDN");
        Employee employee2 = new Employee(2, "Baba", "AUS");
        employeeService.save(employee1);
        employeeService.save(employee2);

        List<Employee> employees = employeeService.findAll();
        assertEquals(2, employees.size());
        assertTrue(employees.contains(employee1));
        assertTrue(employees.contains(employee2));
    }

    @Test
    public void testFindOne() {
        Employee employee = new Employee(1, "Bobo", "IDN");
        employeeService.save(employee);

        Employee foundEmployee = employeeService.findOne(1);
        assertNotNull(foundEmployee);
        assertEquals(1, foundEmployee.getId());
        assertEquals("Bobo", foundEmployee.getName());
    }

    @Test
    public void testSave() {
        Employee employee = new Employee(1, "Bobo", "IDN");
        employeeService.save(employee);

        List<Employee> employees = employeeService.findAll();
        assertEquals(1, employees.size());
        assertTrue(employees.contains(employee));
    }

    @Test
    public void testDelete() {
        Employee employee = new Employee(1, "Bobo", "IDN");
        employeeService.save(employee);
        employeeService.delete(1);

        List<Employee> employees = employeeService.findAll();
        assertEquals(0, employees.size());
    }

}

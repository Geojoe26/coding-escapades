package com.aston.joel.employeeapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(EmployeeController.class)
public class TestEmployeeController {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private EmployeeController controller;

    @Test
    public void testAddEmployee() throws MalformedURLException {

        EmployeeController.employeeInfo e1 = new EmployeeController.employeeInfo("Joel",29,10000.0,"SJD");

        HttpEntity<EmployeeController.employeeInfo> request = new HttpEntity<>(e1);

        when(controller.addEmployee(e1)).thenReturn("Added");

        URL url = new URL("http://localhost:" + port + "/AddEmployee");
        String response = restTemplate.postForObject(url.toString(), request, String.class);

        assertNotNull(response);
        System.out.println(response.toString());

    }

    @Test
    public void testGetEmployee() throws MalformedURLException {
        Employee e = new Employee();
        e.setName("Joel");
        e.setAge(29);
        e.setSalary(45000.0);
        e.setPosition("SJD");

        List<Employee> empList = new ArrayList<>();
        empList.add(e);

        when(controller.getEmployee()).thenReturn(empList);

        URL url = new URL("http://localhost:" + port + "/ListEmployees");
        List<Employee> response = restTemplate.getForObject(url.toString(), List.class);

        assertNotNull(response);
    }
}

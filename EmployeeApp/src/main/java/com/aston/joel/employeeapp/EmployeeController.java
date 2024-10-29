package com.aston.joel.employeeapp;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository empRep;

    public EmployeeController(EmployeeRepository empRep) {
        this.empRep = empRep;
    }

    record employeeInfo(String name, Integer age, Double salary, String position){

        @Override
        public String name() {
            return name;
        }

        @Override
        public Integer age() {
            return age;
        }

        @Override
        public Double salary() {
            return salary;
        }

        @Override
        public String position() {
            return position;
        }
    }

    @RequestMapping(value = "/AddEmployee", method = RequestMethod.POST)
    public String addEmployee(@RequestBody employeeInfo emp)
    {
        if(emp!=null){
            Employee e = new Employee();
            e.setName(emp.name());
            e.setAge(emp.age());
            e.setSalary(emp.salary());
            e.setPosition(emp.position());

            empRep.save(e);
            return "Added";
        }
        else {
            return "Invalid details";
        }
    }

    @RequestMapping(value="/ListEmployees", method = RequestMethod.GET)
    public List<Employee> getEmployee()
    {
        return empRep.findAll();
    }

    public EmployeeRepository getEmpRep() {
        return empRep;
    }
}

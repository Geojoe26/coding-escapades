package com.aston.joel.employeeapp;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Employee {

    private String name;
    private Integer age;
    private Double salary;
    @Id
    @SequenceGenerator(name = "employee_Id_sequence",
    sequenceName = "employee_Id_sequence",
    allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "employee_Id_sequence")
    private Integer Id;
    private String position;

    public Employee(){}
    public Employee(String name, Integer age, Double salary, Integer id, String position) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        Id = id;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", Id=" + Id +
                ", position='" + position + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(age, employee.age) && Objects.equals(salary, employee.salary) && Objects.equals(Id, employee.Id) && Objects.equals(position, employee.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, salary, Id, position);
    }
}

package com.aston.joel.classes;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Candidate {

    private String name;
    private Integer age;
    private String email;
    @Id
    @SequenceGenerator(name = "candidate_id_sequence",
    sequenceName = "candidate_id_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "candidate_id_sequence")
    private Integer id;

    public Candidate(String name, Integer age, String email, Integer id) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.id = id;
    }

    public Candidate() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(name, candidate.name) && Objects.equals(age, candidate.age) && Objects.equals(email, candidate.email) && Objects.equals(id, candidate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, email, id);
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}

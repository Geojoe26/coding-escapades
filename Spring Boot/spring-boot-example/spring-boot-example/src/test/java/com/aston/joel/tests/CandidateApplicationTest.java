package com.aston.joel.tests;

import com.aston.joel.classes.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class CandidateApplicationTest {

    public static void main(String[] args) {
        SpringApplication.from(Main::main).with(MainTest.class).run(args);
    }

}


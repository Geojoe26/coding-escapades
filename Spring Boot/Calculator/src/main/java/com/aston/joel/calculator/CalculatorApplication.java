package com.aston.joel.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class CalculatorApplication {
	public static void main(String[] args) {

		SpringApplication.run(CalculatorApplication.class, args);

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Value A:");
		int a = sc.nextInt();

		System.out.println("Enter Value B:");
		int b = sc.nextInt();



		CalcOperations o1 = new CalcOperations();
		System.out.println(o1.Add(a,b));
		System.out.println(o1.Subtract(a,b));
		System.out.println(o1.Multiply(a,b));
		System.out.println(o1.Divide(a,b));

	}

}

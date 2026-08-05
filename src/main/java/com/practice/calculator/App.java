package com.practice.calculator;

/**
 * Entry point. Lets you run the packaged jar with:
 *   java -jar target/maven-lifecycle-demo.jar
 */
public class App {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println("Maven Lifecycle Demo");
        System.out.println("5 + 3 = " + calc.add(5, 3));
        System.out.println("5 - 3 = " + calc.subtract(5, 3));
        System.out.println("5 * 3 = " + calc.multiply(5, 3));
        System.out.println("5 / 3 = " + calc.divide(5, 3));
    }
}

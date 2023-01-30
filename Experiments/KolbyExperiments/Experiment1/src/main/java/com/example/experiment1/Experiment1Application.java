package com.example.experiment1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Experiment1Application {

    public static void main(String[] args) {
        SpringApplication.run(Experiment1Application.class, args);

        Scanner scan = new Scanner(System.in);

        System.out.println("This is my very first experiment with Springboot.");
        System.out.println();
        System.out.println("Please enter a username:");
        String user = scan.next();

        System.out.println();
        System.out.println("Please enter a password:");
        String pass = scan.next();

        System.out.println("Great Work! You have now created an account with username: " + user);



    }

}

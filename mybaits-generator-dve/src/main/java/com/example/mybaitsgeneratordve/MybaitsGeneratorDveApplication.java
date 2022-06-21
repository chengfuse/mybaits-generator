package com.example.mybaitsgeneratordve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan("com.*")
public class MybaitsGeneratorDveApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybaitsGeneratorDveApplication.class, args);
    }

}

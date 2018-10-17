package com.example.tododemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TodoListDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoListDemoApplication.class, args);
    }
}
package com.example.tododemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.tododemo.model.Category;
import com.example.tododemo.model.TodoItem;
import com.example.tododemo.repository.CategoryRepository;
import com.example.tododemo.repository.TodoListRepository;

@SpringBootApplication
public class TodoListDemoApplication {
	@Autowired
	TodoListRepository todoRepository;
	 
	@Autowired
	CategoryRepository catRepository;

    public static void main(String[] args) {
        SpringApplication.run(TodoListDemoApplication.class, args);
    }

    public void run(String... args) throws Exception {
        // Cleanup the tables
    	todoRepository.deleteAll();
    	catRepository.deleteAll();
    	// Create a Post
    	TodoItem post = new TodoItem("1",null);

        // Create two tags
        Category tag1 = new Category("Spring Boot", null);
        Category tag2 = new Category("Hibernate", null);


        // Add tag references in the post
        post.getTags().add(tag1);
        post.getTags().add(tag2);

        // Add post reference in the tags
        tag1.getItems().add(post);

        todoRepository.save(post);
        catRepository.save(tag1);

    } 	
}
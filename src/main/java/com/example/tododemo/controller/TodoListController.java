package com.example.tododemo.controller;

import java.util.List;

import org.springframework.http.MediaType;

import javax.validation.Valid;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tododemo.model.TodoItem;
import com.example.tododemo.repository.TodoListRepository;

@RestController
public class TodoListController {

    @Autowired
    private TodoListRepository repository;

    @GetMapping("/todoItems")
    public List<TodoItem> getTodoLists() {
        List<TodoItem> list = repository.findAll();
        return list;
    }

    @PostMapping("/todoItems")
    TodoItem newList(@RequestBody TodoItem newObject) {
		return repository.save(newObject);
	}
    
    @DeleteMapping("/todoItems/{id}")
	void deleteEmployee(@PathVariable int id) {
		repository.deleteById(id);
	}
}
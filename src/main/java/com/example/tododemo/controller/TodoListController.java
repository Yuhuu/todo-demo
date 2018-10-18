package com.example.tododemo.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tododemo.model.TodoItem;
import com.example.tododemo.repository.TodoListRepository;

@RestController
public class TodoListController {

    @Autowired
    private TodoListRepository repository;

    @GetMapping("/todoitem")
    public Page<TodoItem> getTodoLists(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @PostMapping("/todoitem")
    public TodoItem createQuestion(@Valid @RequestBody TodoItem item) {
        return repository.save(item);
    }

    
    @DeleteMapping("/todoitem/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable int id) {
        return repository.findById(id)
                .map(item -> {
                    repository.delete(item);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + id));
    }
}
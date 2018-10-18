package com.example.tododemo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tododemo.model.Category;
import com.example.tododemo.model.TodoItem;
 
public interface CategoryRepository extends JpaRepository<Category, Long>{
}
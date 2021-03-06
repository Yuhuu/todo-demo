package com.example.tododemo.repository;
import java.util.List;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.tododemo.model.TodoItem;

public interface TodoListRepository extends JpaRepository<TodoItem, Integer>{

	void deleteById(Integer id);
	 
}
package com.example.tododemo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.tododemo.model.TodoItem;
import com.example.tododemo.repository.TodoListRepository;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.not;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class TodoListRepositoryTest {
	@Autowired
	TodoListRepository repository;
	
	@Autowired
	private TestEntityManager entityManager;
	 
	@Test
	public void should_find_todos_if_repository_is_not_empty() {
		
		TodoItem todoItem = repository.save(new TodoItem("Despription", null));
		entityManager.persist(todoItem);
		Iterable<TodoItem> todoItems = repository.findAll();
		assertThat(todoItems).isNotEmpty();
	}
 
	@Test
	public void should_store_a_todo() {
		
		TodoItem todoItem = repository.save(new TodoItem("OK", null));

 
		assertThat(todoItem).hasFieldOrPropertyWithValue("description", "OK");
	}
 
 
	@Test
	public void should_find_all_todos() {
		TodoItem todoItem1 = new TodoItem("AAA", null);
		entityManager.persist(todoItem1);
 
		TodoItem todoItem2 = new TodoItem("CCC", null);
		entityManager.persist(todoItem2);
 
		TodoItem todoItem3 = new TodoItem("DDD", null);
		entityManager.persist(todoItem3);
 
		Iterable<TodoItem> todoItems = repository.findAll();
 
		assertThat(todoItems).contains(todoItem1, todoItem2, todoItem3);
	}
	
	@Test
	public void should_delete_one_todo() {
		TodoItem todoItem1 = new TodoItem("AAA", null);
		entityManager.persist(todoItem1);
 
		TodoItem todoItem2 = new TodoItem("CCC", null);
		entityManager.persist(todoItem2);
 
		TodoItem todoItem3 = new TodoItem("DDD", null);
		entityManager.persist(todoItem3);
 
		repository.delete(todoItem1);
		Iterable<TodoItem> todoItems = repository.findAll();
		List<TodoItem> shouldNotBeInList = Arrays.asList(todoItem1);
		assertThat(todoItems).contains(todoItem2, todoItem3);
	}
 
	@Test
	public void should_find_items_by_id() {
 
		Optional<TodoItem> todoItem2 = repository.findById(52);
		assertThat(todoItem2.get().getTags()).hasSize(1);
	}
 

}

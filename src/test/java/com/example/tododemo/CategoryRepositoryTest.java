package com.example.tododemo;

import static org.junit.Assert.*;

import java.util.ArrayList;
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

import com.example.tododemo.model.Category;
import com.example.tododemo.model.TodoItem;
import com.example.tododemo.repository.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {
	@Autowired
	CategoryRepository repository;
	
	@Autowired
	private TestEntityManager entityManager;
	 
	@Test
	public void should_find_categorys_if_repository_is_not_empty() {
		
		Category category = repository.save(new Category("Jack", null));
		entityManager.persist(category);
		Iterable<Category> categorys = repository.findAll();
		assertThat(categorys).isNotEmpty();
	}
 
	@Test
	public void should_store_a_category() {
		
		Category category = repository.save(new Category("Jack", null));

 
		assertThat(category).hasFieldOrPropertyWithValue("description", "Jack");
	}
 
 
	@Test
	public void should_find_all_categorys() {
		Category category1 = new Category("AAA", null);
		List<Category> tags = new ArrayList<Category>();
		TodoItem item = new TodoItem("Description",tags);
		List<TodoItem> items = new ArrayList<TodoItem>();
		items.add(item);
		category1.setItems(items);
		entityManager.persist(category1);
 
		Category category2 = new Category("CCC", null);
		entityManager.persist(category2);
 
		Category category3 = new Category("DDD", null);
		entityManager.persist(category3);
 
		Iterable<Category> categorys = repository.findAll();
 
		assertThat(categorys).contains(category1, category2, category3);
	}
 
	@Test
	public void should_find_items_by_id() {
 
		Optional<Category> category2 = repository.findById(Long.valueOf(52));
		assertThat(category2.get().getItems()).hasSize(1);
	}
 

}

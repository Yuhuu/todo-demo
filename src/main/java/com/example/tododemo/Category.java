package com.example.tododemo;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(generator = "item_generator")
    @SequenceGenerator(
            name = "item_generator",
            sequenceName = "item_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(columnDefinition = "text")
    private String description;
    
    @ManyToMany
    @JoinTable(
        name = "post_tag",
        joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "todolist_id",referencedColumnName = "id"))
    private List<TodoItem> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TodoItem> getItems() {
		return items;
	}

	public void setItems(List<TodoItem> items) {
		this.items = items;
	}
}
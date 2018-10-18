package com.example.tododemo.model;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "todo_item")
public class TodoItem {

    @Id
    @GeneratedValue(generator = "item_generator")
    @SequenceGenerator(
            name = "item_generator",
            sequenceName = "item_sequence",
            initialValue = 1000
    )
    private int id;
    
    @Column(name = "created_on")
    private Date createdOn;

    @Column(columnDefinition = "text")
    private String description;
    
    @ManyToMany
    @JoinTable(
        name = "post_tag",
        joinColumns = @JoinColumn(name = "todolist_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id",referencedColumnName = "id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Category> tags;
    


	@PrePersist
    public void prePersist() {
        createdOn = new Date();
    }

    public TodoItem() {
	}
    
    public TodoItem(String description, List<Category> tags){
		this.description = description;
		this.tags = tags;
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    public List<Category> getTags() {
		return tags;
	}

	public void setTags(List<Category> tags) {
		this.tags = tags;
	}
}
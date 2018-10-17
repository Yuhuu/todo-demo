package com.example.tododemo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "todo-item")
public class TodoItem {

    @Id
    @GeneratedValue
    private int id;
    
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "deleted", nullable = true)
    private boolean deleted;

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
        createdOn = LocalDateTime.now();
        deleted = false;
    }

    public TodoItem() {
	}
    
    public TodoItem(int id, List<Category> tags){
		this.id = id;
		this.tags = tags;
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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
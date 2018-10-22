package com.example.tododemo.model;

public class TodoItemForm {

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getTagsId() {
		return tagsId;
	}

	public void setTagsId(Long tagsId) {
		this.tagsId = tagsId;
	}

	private String description;
    
    private Long tagsId;
    


	
}
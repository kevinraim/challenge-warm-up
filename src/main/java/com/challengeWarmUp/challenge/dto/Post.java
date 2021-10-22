package com.challengeWarmUp.challenge.dto;

import java.util.Calendar;

import com.challengeWarmUp.challenge.models.Category;

public class Post {
	private Long id;
	private String title;
	private String image;
	private Category category;
	private Calendar date;
	
	public Post(Long id, String title, String image, Category category, Calendar date) {
		this.id = id;
		this.title = title;
		this.image = image;
		this.category = category;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
	
	
	
}

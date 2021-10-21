package com.challengeWarmUp.challenge.dto;

import java.util.Calendar;

public class Post {
	private Long id;
	private String title;
	private String image;
	private String category;
	private Calendar date;
	
	public Post(Long id, String title, String image, String category, Calendar date) {
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
	
	
	
}

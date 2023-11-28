package com.petsociety.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tbltrivia")
public class TriviaEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int triviaID;
	
    private String title;
    private String content;
    private String category;
    private String author;
    private boolean isDeleted;
	
    public TriviaEntity() {
		super();
	}

	public TriviaEntity(int triviaID, String title, String content, String category, String author, boolean isDeleted) {
		super();
		this.triviaID = triviaID;
		this.title = title;
		this.content = content;
		this.category = category;
		this.author = author;
		this.isDeleted = isDeleted;
	}

	public int getTriviaID() {
		return triviaID;
	}

	public void setTriviaID(int triviaID) {
		this.triviaID = triviaID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
    
    public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
    
}


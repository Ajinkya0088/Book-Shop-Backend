package com.library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	
	private  int id;
	private String book;
	private String author;
	private String department;
	private String publication;
	private int totalbook;
	
	
	public Book(int id, String book, String author, String department, String publication, int totalbook) {
		this.id = id;
		this.book = book;
		this.author = author;
		this.department = department;
		this.publication = publication;
		this.totalbook = totalbook;
	}
	
	
	public Book() {
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	public int getTotalbook() {
		return totalbook;
	}
	public void setTotalbook(int totalbook) {
		this.totalbook = totalbook;
	}
	
	
	

}

package com.library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private String standard;
	private String division;
	public Student() {
		super();
	}
	private String book;
	private String days;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public Student(int id, String name, String standard, String division, String book, String days) {
		super();
		this.id = id;
		this.name = name;
		this.standard = standard;
		this.division = division;
		this.book = book;
		this.days = days;
	}
	
	

}

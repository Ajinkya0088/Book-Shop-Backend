package com.library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.model.Book;
import com.library.repository.BookRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@PostMapping("/book")
	public Book addbook(@RequestBody Book book)
	{
		return bookRepository.save(book);
	}
	
	@GetMapping("/books")
	public List<Book> getbooks()
	{
		return bookRepository.findAll();
	}
	
	@GetMapping("/books/{id}")
	public Optional<Book> getbook(@PathVariable int id)
	{
		return bookRepository.findById(id);
	}
	
	@DeleteMapping("/books/{id}")
	public void deletebook(@PathVariable int id)
	{
		bookRepository.deleteById(id);
	}
	
	@PutMapping("/books/{id}")
	public void updatebook(@RequestBody Book book ,@PathVariable int id)
	{
		Book b=bookRepository.getOne(id);
		//b.setId(book.getId());
		b.setBook(book.getBook());
		b.setDepartment(book.getDepartment());
		b.setPublication(book.getPublication());
		b.setAuthor(book.getAuthor());
		b.setTotalbook(book.getTotalbook());
		
		bookRepository.save(b);
		
	}
}
